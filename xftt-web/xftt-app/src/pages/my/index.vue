<script setup lang="ts">
import { useUserStore } from '../../store/user'

const userStore = useUserStore()

function goLogin(): void {
  uni.navigateTo({ url: '/pages/login/index' })
}

function handleLogout(): void {
  userStore.logout()
}
</script>

<template>
  <view class="my">
    <!-- 今日头条式红色头部：昵称 + 数据统计 -->
    <view class="my-header">
      <view class="my-user">
        <view class="my-avatar">{{ userStore.isAuthenticated ? (userStore.nickName || '用户').slice(0, 1) : '客' }}</view>
        <text class="my-name">{{ userStore.isAuthenticated ? (userStore.nickName || '用户') : '未登录' }}</text>
      </view>
      <view class="my-stats">
        <view class="my-stat"><text class="my-stat-num">0</text><text class="my-stat-label">关注</text></view>
        <view class="my-stat"><text class="my-stat-num">0</text><text class="my-stat-label">粉丝</text></view>
        <view class="my-stat"><text class="my-stat-num">0</text><text class="my-stat-label">收藏</text></view>
      </view>
    </view>

    <view class="my-menu">
      <view class="my-menu-item"><text>我的收藏</text><text class="my-menu-arrow">›</text></view>
      <view class="my-menu-item"><text>我的关注</text><text class="my-menu-arrow">›</text></view>
      <view class="my-menu-item"><text>我的粉丝</text><text class="my-menu-arrow">›</text></view>
      <view class="my-menu-item"><text>浏览历史</text><text class="my-menu-arrow">›</text></view>
      <view v-if="userStore.isAuthenticated" class="my-menu-item" @tap="handleLogout">
        <text>退出登录</text><text class="my-menu-arrow">›</text>
      </view>
      <view v-else class="my-menu-item" @tap="goLogin">
        <text>去登录</text><text class="my-menu-arrow">›</text>
      </view>
    </view>
  </view>
</template>

<style lang="scss" scoped>
.my {
  min-height: 100vh;
  background-color: #f5f5f5;
}
.my-header {
  padding: 64rpx 40rpx 48rpx;
  background: linear-gradient(135deg, #d43c33, #a8261d);
  color: #ffffff;
}
.my-user {
  display: flex;
  align-items: center;
}
.my-avatar {
  width: 110rpx;
  height: 110rpx;
  border-radius: 50%;
  background-color: rgba(255, 255, 255, 0.25);
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 48rpx;
  font-weight: bold;
}
.my-name {
  margin-left: 28rpx;
  font-size: 40rpx;
  font-weight: bold;
}
.my-stats {
  display: flex;
  margin-top: 48rpx;
}
.my-stat {
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.my-stat-num {
  font-size: 36rpx;
  font-weight: bold;
}
.my-stat-label {
  margin-top: 8rpx;
  font-size: 24rpx;
  opacity: 0.85;
}
.my-menu {
  margin-top: 16rpx;
  background-color: #ffffff;
}
.my-menu-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 32rpx 40rpx;
  border-bottom: 1rpx solid #f2f2f2;
  color: #222222;
  font-size: 30rpx;
}
.my-menu-arrow {
  color: #cccccc;
  font-size: 34rpx;
}
</style>
