<script setup lang="ts">
import { ref } from 'vue'
import { onPullDownRefresh, onReachBottom } from '@dcloudio/uni-app'
import { getPage } from '../../api/article'
import type { ArticleItem } from '../../types/api'

const articles = ref<ArticleItem[]>([])
const page = ref(1)
const hasMore = ref(true)
const isLoading = ref(false)

async function loadArticles(): Promise<void> {
  if (isLoading.value || !hasMore.value) return
  isLoading.value = true
  try {
    const result = await getPage({ page: page.value, size: 20 })
    articles.value.push(...(result.rows || []))
    hasMore.value = articles.value.length < (result.total || 0)
    page.value += 1
  } finally {
    isLoading.value = false
  }
}

function openDetail(id: number): void {
  uni.navigateTo({ url: `/pages/detail/index?id=${id}` })
}

onPullDownRefresh(async () => {
  page.value = 1
  articles.value = []
  hasMore.value = true
  await loadArticles()
  uni.stopPullDownRefresh()
})

onReachBottom(() => {
  loadArticles()
})

loadArticles()
</script>

<template>
  <view class="feed">
    <view v-if="articles.length === 0 && !isLoading" class="feed-empty">
      <text>暂无文章</text>
    </view>
    <view v-for="item in articles" :key="item.id" class="feed-item" @tap="openDetail(item.id)">
      <view class="feed-item-body">
        <text class="feed-item-title">{{ item.title }}</text>
        <text class="feed-item-meta">{{ item.authorName || '心飞头条' }}</text>
      </view>
      <image v-if="item.cover" class="feed-item-cover" :src="item.cover" mode="aspectFill" />
    </view>
    <view v-if="isLoading" class="feed-loading"><text>加载中...</text></view>
    <view v-else-if="!hasMore && articles.length > 0" class="feed-loading"><text>没有更多了</text></view>
  </view>
</template>

<style lang="scss" scoped>
.feed {
  padding: 16rpx 24rpx;
}
.feed-item {
  display: flex;
  justify-content: space-between;
  padding: 24rpx 0;
  border-bottom: 1rpx solid #eeeeee;
}
.feed-item-body {
  flex: 1;
  margin-right: 24rpx;
}
.feed-item-title {
  display: block;
  font-size: 32rpx;
  color: #333333;
  line-height: 1.4;
}
.feed-item-meta {
  display: block;
  margin-top: 12rpx;
  font-size: 24rpx;
  color: #999999;
}
.feed-item-cover {
  width: 200rpx;
  height: 140rpx;
  border-radius: 8rpx;
  flex-shrink: 0;
}
.feed-empty,
.feed-loading {
  padding: 40rpx 0;
  text-align: center;
  color: #999999;
}
</style>
