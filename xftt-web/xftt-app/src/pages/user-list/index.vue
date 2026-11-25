<script setup lang="ts">
import { computed, ref } from 'vue'
import { onPullDownRefresh, onLoad, onReachBottom } from '@dcloudio/uni-app'
import {
  getCollections,
  getFans,
  getFollows,
  type CollectionItem,
  type UserFanItem,
  type UserFollowItem,
} from '../../api/user'
import { getToken } from '../../utils/request'

type ListType = 'follows' | 'fans' | 'collections'

const type = ref<ListType>('follows')
const page = ref(1)
const size = 10
const total = ref(0)
const loading = ref(false)
const loaded = ref(false)

const followRows = ref<UserFollowItem[]>([])
const fanRows = ref<UserFanItem[]>([])
const collectionRows = ref<CollectionItem[]>([])

const titles: Record<ListType, string> = {
  follows: '我的关注',
  fans: '我的粉丝',
  collections: '我的收藏',
}

const emptyTexts: Record<ListType, string> = {
  follows: '还没有关注任何人',
  fans: '还没有粉丝',
  collections: '还没有收藏任何文章',
}

const pageTitle = computed(() => titles[type.value] || '列表')

/** 当前类型已加载的行数 */
const currentRowsLength = computed(() => {
  if (type.value === 'follows') return followRows.value.length
  if (type.value === 'fans') return fanRows.value.length
  return collectionRows.value.length
})

/** 是否还有更多 */
function hasMore(): boolean {
  return currentRowsLength.value < total.value
}

/** 统一拉取当前类型的分页数据 */
async function loadRows(reset: boolean): Promise<void> {
  if (!getToken()) {
    uni.navigateTo({ url: '/pages/login/index' })
    return
  }
  if (loading.value) return
  if (!reset && loaded.value && !hasMore()) return

  loading.value = true
  const targetPage = reset ? 1 : page.value
  try {
    if (type.value === 'follows') {
      const result = await getFollows(targetPage, size)
      followRows.value = reset ? result.rows : [...followRows.value, ...result.rows]
      total.value = result.total
    } else if (type.value === 'fans') {
      const result = await getFans(targetPage, size)
      fanRows.value = reset ? result.rows : [...fanRows.value, ...result.rows]
      total.value = result.total
    } else {
      const result = await getCollections(targetPage, size)
      collectionRows.value = reset ? result.rows : [...collectionRows.value, ...result.rows]
      total.value = result.total
    }
    page.value = targetPage + 1
    loaded.value = true
  } catch {
    // 加载失败保留已有数据，空态由 rows 长度判断
  } finally {
    loading.value = false
  }
}

function onItemTap(item: CollectionItem): void {
  if (!item.articleId) return
  uni.navigateTo({ url: `/pages/detail/index?id=${item.articleId}` })
}

function formatTime(time?: string): string {
  return time || ''
}

onLoad((query) => {
  const raw = query?.type as ListType | undefined
  type.value = raw === 'fans' || raw === 'collections' ? raw : 'follows'
  uni.setNavigationBarTitle({ title: pageTitle.value })
  loadRows(true)
})

onPullDownRefresh(async () => {
  page.value = 1
  await loadRows(true)
  uni.stopPullDownRefresh()
})

onReachBottom(() => {
  if (loaded.value && hasMore()) {
    loadRows(false)
  }
})
</script>

<template>
  <view class="user-list">
    <!-- 关注 -->
    <template v-if="type === 'follows'">
      <view v-for="item in followRows" :key="item.id" class="row">
        <view class="row-avatar">{{ (item.followName || '友').slice(0, 1) }}</view>
        <view class="row-body">
          <text class="row-name">{{ item.followName || '头条用户' }}</text>
          <text class="row-time">{{ formatTime(item.createdTime) }}</text>
        </view>
      </view>
    </template>

    <!-- 粉丝 -->
    <template v-else-if="type === 'fans'">
      <view v-for="item in fanRows" :key="item.id" class="row">
        <view class="row-avatar">{{ (item.fansName || '粉').slice(0, 1) }}</view>
        <view class="row-body">
          <text class="row-name">{{ item.fansName || '头条用户' }}</text>
          <text class="row-time">{{ formatTime(item.createdTime) }}</text>
        </view>
      </view>
    </template>

    <!-- 收藏：文章卡片 -->
    <template v-else>
      <view
        v-for="item in collectionRows"
        :key="`${item.articleId}-${item.collectionTime}`"
        class="collection-card"
        @tap="onItemTap(item)"
      >
        <view class="collection-card-body">
          <text class="collection-title">{{ item.title || '文章已删除' }}</text>
          <text class="collection-meta">
            {{ item.authorName || '心飞头条' }} · {{ formatTime(item.collectionTime) }}
          </text>
        </view>
        <image v-if="item.images" class="collection-cover" :src="item.images" mode="aspectFill" />
      </view>
    </template>

    <view v-if="loaded && currentRowsLength === 0" class="empty">
      <text>{{ emptyTexts[type] }}</text>
    </view>
    <view v-if="loading" class="loading"><text>加载中...</text></view>
    <view v-else-if="loaded && !hasMore() && currentRowsLength > 0" class="loading">
      <text>— 没有更多了 —</text>
    </view>
  </view>
</template>

<style lang="scss" scoped>
.user-list {
  min-height: 100vh;
  background-color: #f5f5f5;
}
.row {
  display: flex;
  align-items: center;
  padding: 28rpx 32rpx;
  background-color: #ffffff;
  border-bottom: 1rpx solid #f2f2f2;
}
.row-avatar {
  width: 80rpx;
  height: 80rpx;
  border-radius: 50%;
  background-color: #f0e6e6;
  color: #ED4040;
  font-size: 32rpx;
  font-weight: bold;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
}
.row-body {
  margin-left: 24rpx;
}
.row-name {
  display: block;
  font-size: 30rpx;
  color: #222222;
}
.row-time {
  display: block;
  margin-top: 10rpx;
  font-size: 22rpx;
  color: #bbbbbb;
}
.collection-card {
  display: flex;
  align-items: center;
  padding: 28rpx 32rpx;
  background-color: #ffffff;
  border-bottom: 1rpx solid #f2f2f2;
}
.collection-card-body {
  flex: 1;
  min-width: 0;
}
.collection-title {
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
  font-size: 30rpx;
  color: #222222;
  line-height: 1.45;
}
.collection-meta {
  display: block;
  margin-top: 16rpx;
  font-size: 22rpx;
  color: #999999;
}
.collection-cover {
  width: 190rpx;
  height: 130rpx;
  margin-left: 24rpx;
  border-radius: 12rpx;
  background-color: #f5f5f5;
  flex-shrink: 0;
}
.empty,
.loading {
  padding: 80rpx 0;
  text-align: center;
  color: #bbbbbb;
  font-size: 26rpx;
}
</style>
