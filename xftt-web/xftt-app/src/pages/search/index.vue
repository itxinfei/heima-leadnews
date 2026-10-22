<script setup lang="ts">
import { ref } from 'vue'
import {
  clearSearchHistory,
  loadAssociateWords,
  loadHotKeywords,
  loadSearchHistory,
  searchArticles,
} from '../../api/search'
import type { AssociateWord, HotWord, UserSearchItem } from '../../api/search'
import type { ArticleItem } from '../../types/api'

/** 搜索历史：优先后端接口（APP-F11），失败降级本地存储 */
const HISTORY_KEY = 'xftt-search-history'
const history = ref<UserSearchItem[]>([])
const localHistory = ref<string[]>(uni.getStorageSync(HISTORY_KEY) || [])
const hasServerHistory = ref(false)

/** 热词榜占位：接口失败时使用本地兜底 */
const hotWords = ref<HotWord[]>([])
const LOCAL_HOT_WORDS: string[] = ['科技前沿', '人工智能', '财经早报', '体育赛事', '娱乐八卦']

const keyword = ref('')
const associates = ref<AssociateWord[]>([])
const isSearching = ref(false)
const hasSearched = ref(false)
const results = ref<ArticleItem[]>([])
const isLoadFailed = ref(false)

let associateTimer: ReturnType<typeof setTimeout> | null = null

async function loadHistory(): Promise<void> {
  try {
    const list = await loadSearchHistory()
    if (list) {
      history.value = list
      hasServerHistory.value = true
    }
  } catch {
    hasServerHistory.value = false
  }
}

async function loadHot(): Promise<void> {
  try {
    const list = await loadHotKeywords()
    if (list && list.length > 0) {
      hotWords.value = list
    }
  } catch {
    hotWords.value = []
  }
}

/** 输入联想：300ms 防抖（PRD 10.3） */
function handleInput(value: string): void {
  keyword.value = value
  if (associateTimer) clearTimeout(associateTimer)
  if (!value.trim()) {
    associates.value = []
    return
  }
  associateTimer = setTimeout(async () => {
    try {
      associates.value = (await loadAssociateWords(value.trim())) || []
    } catch {
      associates.value = []
    }
  }, 300)
}

async function handleSearch(word?: string): Promise<void> {
  const target = (word ?? keyword.value).trim()
  if (!target) return
  keyword.value = target
  associates.value = []
  saveHistory(target)
  isSearching.value = true
  isLoadFailed.value = false
  try {
    results.value = (await searchArticles(target)) || []
    hasSearched.value = true
  } catch {
    isLoadFailed.value = true
    results.value = []
  } finally {
    isSearching.value = false
  }
}

function saveHistory(word: string): void {
  if (hasServerHistory.value) {
    // 后端在 esArticleSearch(fromIndex=0) 时自动写入历史，这里仅刷新展示
    loadHistory()
    return
  }
  localHistory.value = [word, ...localHistory.value.filter((item) => item !== word)].slice(0, 20)
  uni.setStorageSync(HISTORY_KEY, localHistory.value)
}

async function handleClearHistory(): Promise<void> {
  if (hasServerHistory.value) {
    await clearSearchHistory()
    history.value = []
  } else {
    localHistory.value = []
    uni.removeStorageSync(HISTORY_KEY)
  }
}

function openDetail(id: number): void {
  uni.navigateTo({ url: `/pages/detail/index?id=${id}` })
}

function handleConfirm(event: { detail: { value: string } }): void {
  handleSearch(event.detail.value)
}

function handleInputEvent(event: unknown): void {
  const value = (event as { detail: { value: string } }).detail.value
  handleInput(value)
}

function removeLocalHistory(keyword: string): void {
  localHistory.value = localHistory.value.filter((item) => item !== keyword)
  uni.setStorageSync(HISTORY_KEY, localHistory.value)
}

loadHistory()
loadHot()
</script>

<template>
  <view class="search">
    <view class="search-input-bar">
      <input
        class="search-input"
        :value="keyword"
        placeholder="搜你想看的"
        confirm-type="search"
        focus
        @input="handleInputEvent"
        @confirm="handleSearch()"
      />
    </view>

    <!-- 联想词 -->
    <view v-if="associates.length > 0" class="search-section">
      <view v-for="word in associates" :key="word.id" class="associate-item" @tap="handleSearch(word.associateWords)">
        <text class="associate-icon">🔍</text>
        <text class="associate-text">{{ word.associateWords }}</text>
      </view>
    </view>

    <!-- 搜索结果 -->
    <view v-else-if="hasSearched" class="search-section">
      <view v-if="isSearching" class="search-empty"><text>搜索中...</text></view>
      <view v-else-if="isLoadFailed" class="search-empty" @tap="handleSearch()">
        <text>搜索失败，点击重试</text>
      </view>
      <view v-else-if="results.length === 0" class="search-empty">
        <text>没有找到相关内容，换个关键词试试</text>
      </view>
      <view v-for="item in results" :key="item.id" class="result-item" @tap="openDetail(item.id)">
        <text class="result-title">{{ item.title }}</text>
        <text class="result-meta">{{ item.authorName || '心飞头条' }}</text>
      </view>
    </view>

    <template v-else>
      <!-- 搜索历史 -->
      <view v-if="history.length > 0 || localHistory.length > 0" class="search-section">
        <view class="search-section-head">
          <text class="search-section-title">搜索历史</text>
          <text class="search-section-clear" @tap="handleClearHistory">清空</text>
        </view>
        <view class="search-tags">
          <view v-for="item in history" :key="item.id" class="search-tag" @tap="handleSearch(item.keyword)">
            <text class="search-tag-text">{{ item.keyword }}</text>
          </view>
          <view v-for="item in localHistory" :key="item" class="search-tag" @tap="handleSearch(item)">
            <text class="search-tag-text">{{ item }}</text>
            <text class="search-tag-close" @tap.stop="removeLocalHistory(item)">×</text>
          </view>
        </view>
      </view>

      <!-- 热词榜 -->
      <view class="search-section">
        <text class="search-section-title">猜你想搜</text>
        <view class="search-tags">
          <view v-for="(word, index) in hotWords" :key="word.id" class="search-tag" @tap="handleSearch(word.hotWords)">
            <text class="search-tag-rank" :class="{ 'search-tag-rank--top': index < 3 }">{{ index + 1 }}</text>
            <text class="search-tag-text">{{ word.hotWords }}</text>
          </view>
          <view v-for="(word, index) in (hotWords.length === 0 ? LOCAL_HOT_WORDS : [])" :key="word" class="search-tag" @tap="handleSearch(word)">
            <text class="search-tag-rank" :class="{ 'search-tag-rank--top': index < 3 }">{{ index + 1 }}</text>
            <text class="search-tag-text">{{ word }}</text>
          </view>
        </view>
      </view>
    </template>
  </view>
</template>

<style lang="scss" scoped>
.search {
  min-height: 100vh;
  background-color: #ffffff;
  padding: 24rpx 32rpx;
}
.search-input-bar {
  height: 72rpx;
  background-color: #f5f5f5;
  border-radius: 36rpx;
  display: flex;
  align-items: center;
  padding: 0 28rpx;
}
.search-input {
  flex: 1;
  font-size: 28rpx;
}
.search-section {
  margin-top: 40rpx;
}
.search-section-head {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.search-section-title {
  font-size: 30rpx;
  font-weight: bold;
  color: #222222;
}
.search-section-clear {
  font-size: 24rpx;
  color: #999999;
}
.search-tags {
  display: flex;
  flex-wrap: wrap;
  gap: 20rpx;
  margin-top: 24rpx;
}
.search-tag {
  display: flex;
  align-items: center;
  height: 56rpx;
  padding: 0 20rpx;
  background-color: #f5f5f5;
  border-radius: 28rpx;
}
.search-tag-rank {
  font-size: 24rpx;
  font-weight: bold;
  color: #999999;
  margin-right: 8rpx;
}
.search-tag-rank--top {
  color: #ED4040;
}
.search-tag-text {
  font-size: 26rpx;
  color: #333333;
}
.search-tag-close {
  margin-left: 12rpx;
  font-size: 30rpx;
  color: #bbbbbb;
}
.associate-item {
  display: flex;
  align-items: center;
  padding: 24rpx 0;
  border-bottom: 1rpx solid #f2f2f2;
}
.associate-icon {
  font-size: 26rpx;
  margin-right: 20rpx;
  color: #999999;
}
.associate-text {
  font-size: 28rpx;
  color: #222222;
}
.search-empty {
  padding: 80rpx 0;
  text-align: center;
  color: #bbbbbb;
}
.result-item {
  padding: 28rpx 0;
  border-bottom: 1rpx solid #f2f2f2;
}
.result-title {
  display: block;
  font-size: 32rpx;
  font-weight: bold;
  color: #222222;
  line-height: 1.45;
}
.result-meta {
  display: block;
  margin-top: 12rpx;
  font-size: 24rpx;
  color: #999999;
}
</style>
