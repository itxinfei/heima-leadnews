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
    <view class="my-header">
      <view class="my-avatar">
        <text>{{ userStore.isAuthenticated ? (userStore.nickName || '用户') : '未登录' }}</text>
      </view>
    </view>
    <view class="my-menu">
      <view class="my-menu-item"><text>我的收藏</text></view>
      <view class="my-menu-item"><text>我的关注</text></view>
      <view class="my-menu-item"><text>我的粉丝</text></view>
      <view v-if="userStore.isAuthenticated" class="my-menu-item" @tap="handleLogout">
        <text>退出登录</text>
      </view>
      <view v-else class="my-menu-item" @tap="goLogin"><text>去登录</text></view>
    </view>
  </view>
</template>

<style lang="scss" scoped>
.my {
  min-height: 100vh;
}
.my-header {
  padding: 80rpx 32rpx;
  background-color: #e64a19;
}
.my-avatar {
  font-size: 36rpx;
  color: #ffffff;
  font-weight: bold;
}
.my-menu {
  margin-top: 16rpx;
  background-color: #ffffff;
}
.my-menu-item {
  padding: 32rpx;
  border-bottom: 1rpx solid #f0f0f0;
  color: #333333;
}
</style>
