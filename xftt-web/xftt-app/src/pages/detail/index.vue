<script setup lang="ts">
import { reactive, ref } from 'vue'
import { onLoad } from '@dcloudio/uni-app'
import { getDetail } from '../../api/article'
import { createComment, getCommentList } from '../../api/comment'
import { uploadReadBehavior } from '../../api/behavior'
import { getToken } from '../../utils/request'
import type { ArticleDetail } from '../../types/api'
import type { CommentItem } from '../../api/comment'

const article = ref<ArticleDetail | null>(null)
const articleId = ref(0)
const comments = ref<CommentItem[]>([])
const isLiked = ref(false)
const isFavorited = ref(false)
const commentInputVisible = ref(false)
const draft = reactive({ content: '' })

/** 载入评论列表；接口不可用时不打断详情页 */
async function loadComments(): Promise<void> {
  try {
    const list = await getCommentList(articleId.value)
    comments.value = list || []
  } catch {
    comments.value = []
  }
}

function openCommentInput(): void {
  if (!getToken()) {
    uni.navigateTo({ url: '/pages/login/index' })
    return
  }
  commentInputVisible.value = true
}

async function submitComment(): Promise<void> {
  const content = draft.content.trim()
  if (!content) {
    uni.showToast({ title: '请输入评论内容', icon: 'none' })
    return
  }
  await createComment({ articleId: articleId.value, content })
  draft.content = ''
  commentInputVisible.value = false
  uni.showToast({ title: '评论成功', icon: 'success' })
  loadComments()
}

function handleLike(): void {
  if (!getToken()) {
    uni.navigateTo({ url: '/pages/login/index' })
    return
  }
  isLiked.value = !isLiked.value
}

function handleFavorite(): void {
  if (!getToken()) {
    uni.navigateTo({ url: '/pages/login/index' })
    return
  }
  isFavorited.value = !isFavorited.value
}

function handleShare(): void {
  // H5 端复制链接分享；App 端可替换为 uni.share 调起系统分享
  uni.setClipboardData({
    data: `${location.origin}/#/pages/detail/index?id=${articleId.value}`,
    success: () => {
      uni.showToast({ title: '链接已复制', icon: 'none' })
    },
  })
}

function formatTime(time?: string): string {
  return time || ''
}

onLoad(async (query) => {
  const id = Number(query?.id)
  if (!id) return
  articleId.value = id
  article.value = await getDetail(id)
  if (article.value?.title) {
    uni.setNavigationBarTitle({ title: article.value.title })
  }
  loadComments()
  // 阅读行为上报：失败静默
  uploadReadBehavior(id)
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

      <!-- 评论区 -->
      <view class="comments">
        <view class="comments-title">
          <text>热门评论</text>
          <text class="comments-count">{{ comments.length }}</text>
        </view>
        <view v-if="comments.length === 0" class="comments-empty"><text>抢首评</text></view>
        <view v-for="item in comments" :key="item.id" class="comment-item">
          <view class="comment-avatar">{{ (item.authorName || '评').slice(0, 1) }}</view>
          <view class="comment-body">
            <text class="comment-author">{{ item.authorName || '头条用户' }}</text>
            <text class="comment-content">{{ item.content }}</text>
            <view class="comment-meta">
              <text>{{ formatTime(item.createTime) }}</text>
              <text class="comment-likes" v-if="item.likes">👍 {{ item.likes }}</text>
            </view>
          </view>
        </view>
      </view>
    </template>
    <view v-else class="detail-empty"><text>加载中...</text></view>

    <!-- 底部互动栏：评论 / 点赞 / 收藏 / 分享 -->
    <view class="detail-toolbar">
      <view class="toolbar-comment" @tap="openCommentInput">
        <text class="toolbar-comment-placeholder">写评论...</text>
      </view>
      <view class="toolbar-action" :class="{ 'toolbar-action--active': isLiked }" @tap="handleLike">
        <text class="toolbar-icon">👍</text>
        <text class="toolbar-label">点赞</text>
      </view>
      <view class="toolbar-action" :class="{ 'toolbar-action--active': isFavorited }" @tap="handleFavorite">
        <text class="toolbar-icon">⭐</text>
        <text class="toolbar-label">收藏</text>
      </view>
      <view class="toolbar-action" @tap="handleShare">
        <text class="toolbar-icon">↗</text>
        <text class="toolbar-label">分享</text>
      </view>
    </view>

    <!-- 评论输入弹层 -->
    <view v-if="commentInputVisible" class="comment-editor-mask" @tap="commentInputVisible = false">
      <view class="comment-editor" @tap.stop>
        <textarea v-model="draft.content" class="comment-editor-input" :maxlength="500" placeholder="友善评论，理性发言" />
        <button class="comment-editor-send" :disabled="!draft.content.trim()" @tap="submitComment">发送</button>
      </view>
    </view>
  </view>
</template>

<style lang="scss" scoped>
.detail {
  padding: 32rpx 32rpx 140rpx;
  background-color: #ffffff;
  min-height: 100vh;
}
.detail-title {
  display: block;
  font-size: 44rpx;
  font-weight: bold;
  color: #222222;
  line-height: 1.45;
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

.comments {
  margin-top: 64rpx;
  border-top: 1rpx solid #f2f2f2;
  padding-top: 32rpx;
}
.comments-title {
  display: flex;
  align-items: center;
  gap: 12rpx;
  font-size: 32rpx;
  font-weight: bold;
  color: #222222;
  margin-bottom: 24rpx;
}
.comments-count {
  font-size: 24rpx;
  font-weight: normal;
  color: #999999;
}
.comments-empty {
  padding: 48rpx 0;
  text-align: center;
  color: #bbbbbb;
}
.comment-item {
  display: flex;
  padding: 24rpx 0;
  border-bottom: 1rpx solid #f7f7f7;
}
.comment-avatar {
  width: 64rpx;
  height: 64rpx;
  border-radius: 50%;
  background-color: #f0f0f0;
  color: #d43c33;
  font-size: 28rpx;
  font-weight: bold;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-shrink: 0;
}
.comment-body {
  flex: 1;
  margin-left: 20rpx;
}
.comment-author {
  display: block;
  font-size: 24rpx;
  color: #999999;
}
.comment-content {
  display: block;
  margin-top: 12rpx;
  font-size: 28rpx;
  color: #222222;
  line-height: 1.6;
}
.comment-meta {
  display: flex;
  gap: 24rpx;
  margin-top: 12rpx;
  font-size: 22rpx;
  color: #bbbbbb;
}

.detail-toolbar {
  position: fixed;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 20;
  display: flex;
  align-items: center;
  padding: 16rpx 24rpx calc(16rpx + env(safe-area-inset-bottom));
  background-color: #ffffff;
  border-top: 1rpx solid #f2f2f2;
}
.toolbar-comment {
  flex: 1;
  height: 64rpx;
  line-height: 64rpx;
  padding: 0 24rpx;
  background-color: #f5f5f5;
  border-radius: 32rpx;
}
.toolbar-comment-placeholder {
  font-size: 26rpx;
  color: #bbbbbb;
}
.toolbar-action {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-left: 40rpx;
  color: #666666;
}
.toolbar-action--active {
  color: #d43c33;
}
.toolbar-icon {
  font-size: 36rpx;
  line-height: 1;
}
.toolbar-label {
  margin-top: 6rpx;
  font-size: 20rpx;
}

.comment-editor-mask {
  position: fixed;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  z-index: 30;
  background-color: rgba(0, 0, 0, 0.4);
  display: flex;
  align-items: flex-end;
}
.comment-editor {
  width: 100%;
  background-color: #ffffff;
  padding: 24rpx 32rpx calc(24rpx + env(safe-area-inset-bottom));
  border-radius: 24rpx 24rpx 0 0;
}
.comment-editor-input {
  width: 100%;
  height: 160rpx;
  font-size: 28rpx;
}
.comment-editor-send {
  margin-top: 16rpx;
  background-color: #d43c33;
  color: #ffffff;
  font-size: 28rpx;
  border-radius: 40rpx;
}
.comment-editor-send[disabled] {
  background-color: #f4cbc9;
  color: #ffffff;
}
</style>
