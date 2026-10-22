<script setup lang="ts">
import { ref } from 'vue'

/** 搜索历史：本地保存，最多 20 条（APP-F11 的一部分，先落 UI 与本地存储） */
const HISTORY_KEY = 'xftt-search-history'
const history = ref<string[]>(uni.getStorageSync(HISTORY_KEY) || [])

/** 热词榜占位：后续对接 /associate_search 与 Trie 联想 */
const hotWords = ref([
  '科技前沿',
  '人工智能',
  '财经早报',
  '体育赛事',
  '娱乐八卦',
])

function saveHistory(keyword: string): void {
  const next = [keyword, ...history.value.filter((item) => item !== keyword)].slice(0, 20)
  history.value = next
  uni.setStorageSync(HISTORY_KEY, next)
}

function handleSearch(keyword: string): void {
  if (!keyword.trim()) return
  saveHistory(keyword.trim())
  uni.showToast({ title: '搜索功能开发中', icon: 'none' })
}

function clearHistory(): void {
  history.value = []
  uni.removeStorageSync(HISTORY_KEY)
}

function removeHistory(keyword: string): void {
  history.value = history.value.filter((item) => item !== keyword)
  uni.setStorageSync(HISTORY_KEY, history.value)
}

function handleConfirm(event: { detail: { value: string } }): void {
  handleSearch(event.detail.value)
}
</script>

<template>
  <view class="search">
    <view class="search-input-bar">
      <input
        class="search-input"
        placeholder="搜你想看的"
        confirm-type="search"
        @confirm="handleConfirm"
      />
    </view>

    <view v-if="history.length > 0" class="search-section">
      <view class="search-section-head">
        <text class="search-section-title">搜索历史</text>
        <text class="search-section-clear" @tap="clearHistory">清空</text>
      </view>
      <view class="search-tags">
        <view v-for="item in history" :key="item" class="search-tag">
          <text class="search-tag-text" @tap="handleSearch(item)">{{ item }}</text>
          <text class="search-tag-close" @tap.stop="removeHistory(item)">×</text>
        </view>
      </view>
    </view>

    <view class="search-section">
      <text class="search-section-title">猜你想搜</text>
      <view class="search-tags">
        <view v-for="(word, index) in hotWords" :key="word" class="search-tag" @tap="handleSearch(word)">
          <text class="search-tag-rank" :class="{ 'search-tag-rank--top': index < 3 }">{{ index + 1 }}</text>
          <text class="search-tag-text">{{ word }}</text>
        </view>
      </view>
    </view>
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
</style>
