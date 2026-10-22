<script setup lang="ts">
import { ref } from 'vue'
import { onPullDownRefresh, onReachBottom } from '@dcloudio/uni-app'
import { getPage } from '../../api/article'
import { DEFAULT_CHANNELS, getChannelList } from '../../api/channel'
import type { ArticleItem } from '../../types/api'
import type { ChannelOption } from '../../api/channel'

/** 频道栏：从后端动态加载，失败时降级为默认频道；首个固定为"推荐" */
const channels = ref<ChannelOption[]>([{ id: 0, name: '推荐' }, ...DEFAULT_CHANNELS.slice(1)])
const activeChannelId = ref(0)

async function loadChannels(): Promise<void> {
  try {
    const remoteChannels = await getChannelList()
    if (remoteChannels && remoteChannels.length > 0) {
      channels.value = [{ id: 0, name: '推荐' }, ...remoteChannels.filter((channel) => channel.id !== 0)]
    }
  } catch {
    // 保留兜底频道，不打断首页
  }
}

const articles = ref<ArticleItem[]>([])
const page = ref(1)
const hasMore = ref(true)
const isLoading = ref(false)

function selectChannel(id: number): void {
  activeChannelId.value = id
  page.value = 1
  articles.value = []
  hasMore.value = true
  loadArticles()
}

async function loadArticles(): Promise<void> {
  if (isLoading.value || !hasMore.value) return
  isLoading.value = true
  try {
    const result = await getPage({ page: page.value, size: 20, channelId: activeChannelId.value || undefined })
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

loadChannels()
loadArticles()
</script>

<template>
  <view class="feed">
    <!-- 顶部频道栏（今日头条式：横向滚动，选中红色加粗） -->
    <scroll-view class="channel-bar" scroll-x :show-scrollbar="false">
      <view
        v-for="channel in channels"
        :key="channel.id"
        class="channel-item"
        :class="{ 'channel-item--active': channel.id === activeChannelId }"
        @tap="selectChannel(channel.id)"
      >
        <text>{{ channel.name }}</text>
        <view v-if="channel.id === activeChannelId" class="channel-indicator" />
      </view>
    </scroll-view>

    <!-- 信息流 -->
    <view v-if="articles.length === 0 && !isLoading" class="feed-empty">
      <text>暂无文章</text>
    </view>
    <view v-for="item in articles" :key="item.id" class="feed-card" @tap="openDetail(item.id)">
      <view class="feed-card-body">
        <text class="feed-card-title">{{ item.title }}</text>
        <view class="feed-card-meta">
          <text class="feed-card-author">{{ item.authorName || '心飞头条' }}</text>
          <text class="feed-card-dot">·</text>
          <text>{{ item.views ?? 0 }}阅读</text>
          <text class="feed-card-dot">·</text>
          <text>{{ item.publishTime || '' }}</text>
        </view>
      </view>
      <image v-if="item.cover" class="feed-card-cover" :src="item.cover" mode="aspectFill" />
    </view>
    <view v-if="isLoading" class="feed-loading"><text>加载中...</text></view>
    <view v-else-if="!hasMore && articles.length > 0" class="feed-loading"><text>— 没有更多了 —</text></view>
  </view>
</template>

<style lang="scss" scoped>
.feed {
  background-color: #ffffff;
  min-height: 100vh;
}
.channel-bar {
  position: sticky;
  top: 0;
  z-index: 10;
  display: flex;
  white-space: nowrap;
  background-color: #ffffff;
  border-bottom: 1rpx solid #f2f2f2;
  padding: 0 12rpx;
}
.channel-item {
  display: inline-flex;
  flex-direction: column;
  align-items: center;
  padding: 22rpx 20rpx 0;
  font-size: 30rpx;
  color: #222222;
}
.channel-item--active {
  font-weight: bold;
  color: #d43c33;
}
.channel-indicator {
  width: 40rpx;
  height: 6rpx;
  margin-top: 10rpx;
  border-radius: 6rpx;
  background-color: #d43c33;
}
.feed-card {
  display: flex;
  justify-content: space-between;
  padding: 28rpx 32rpx;
  border-bottom: 1rpx solid #f2f2f2;
}
.feed-card-body {
  flex: 1;
  margin-right: 24rpx;
}
.feed-card-title {
  display: block;
  font-size: 34rpx;
  font-weight: bold;
  color: #222222;
  line-height: 1.45;
}
.feed-card-meta {
  display: flex;
  align-items: center;
  margin-top: 16rpx;
  font-size: 24rpx;
  color: #999999;
}
.feed-card-author {
  color: #666666;
}
.feed-card-dot {
  margin: 0 10rpx;
}
.feed-card-cover {
  width: 220rpx;
  height: 150rpx;
  border-radius: 8rpx;
  flex-shrink: 0;
}
.feed-empty,
.feed-loading {
  padding: 48rpx 0;
  text-align: center;
  color: #bbbbbb;
  background-color: #ffffff;
}
</style>
