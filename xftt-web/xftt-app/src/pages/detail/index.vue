<script setup lang="ts">
import { ref } from 'vue'
import { onLoad } from '@dcloudio/uni-app'
import { getDetail } from '../../api/article'
import type { ArticleDetail } from '../../types/api'

const article = ref<ArticleDetail | null>(null)

onLoad(async (query) => {
  const id = Number(query?.id)
  if (!id) return
  article.value = await getDetail(id)
  if (article.value?.title) {
    uni.setNavigationBarTitle({ title: article.value.title })
  }
})
</script>

<template>
  <view class="detail">
    <template v-if="article">
      <text class="detail-title">{{ article.title }}</text>
      <text class="detail-meta">{{ article.authorName || '心飞头条' }}</text>
      <view class="detail-content">
        <rich-text :nodes="article.content" />
      </view>
    </template>
    <view v-else class="detail-empty"><text>加载中...</text></view>
  </view>
</template>

<style lang="scss" scoped>
.detail {
  padding: 32rpx 24rpx;
  background-color: #ffffff;
  min-height: 100vh;
}
.detail-title {
  display: block;
  font-size: 40rpx;
  font-weight: bold;
  color: #333333;
  line-height: 1.4;
}
.detail-meta {
  display: block;
  margin: 24rpx 0;
  font-size: 24rpx;
  color: #999999;
}
.detail-content {
  font-size: 30rpx;
  line-height: 1.8;
}
.detail-empty {
  padding: 80rpx 0;
  text-align: center;
  color: #999999;
}
</style>
