<script setup lang="ts">
import { reactive, ref } from 'vue'
import { login } from '../../api/user'
import { useUserStore } from '../../store/user'

const userStore = useUserStore()
const isLoading = ref(false)
const form = reactive({
  mobile: '',
  password: '',
})

async function handleSubmit(): Promise<void> {
  if (!form.mobile || !form.password) {
    uni.showToast({ title: '请输入手机号和密码', icon: 'none' })
    return
  }
  isLoading.value = true
  try {
    const result = await login({ mobile: form.mobile, password: form.password })
    userStore.applyLogin(result.token, result.userId, result.nickName)
    uni.switchTab({ url: '/pages/index/index' })
  } finally {
    isLoading.value = false
  }
}
</script>

<template>
  <view class="login">
    <text class="login-title">心飞头条</text>
    <view class="login-form">
      <input v-model="form.mobile" class="login-input" type="number" placeholder="请输入手机号" />
      <input v-model="form.password" class="login-input" password placeholder="请输入密码" />
      <button class="login-btn" :disabled="isLoading" @tap="handleSubmit">
        {{ isLoading ? '登录中...' : '登录' }}
      </button>
    </view>
  </view>
</template>

<style lang="scss" scoped>
.login {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 160rpx;
}
.login-title {
  font-size: 48rpx;
  font-weight: bold;
  color: #e64a19;
  margin-bottom: 80rpx;
}
.login-form {
  width: 600rpx;
}
.login-input {
  height: 88rpx;
  margin-bottom: 32rpx;
  padding: 0 24rpx;
  border: 1rpx solid #dddddd;
  border-radius: 8rpx;
  background-color: #ffffff;
}
.login-btn {
  margin-top: 16rpx;
  background-color: #e64a19;
  color: #ffffff;
  border-radius: 8rpx;
}
</style>
