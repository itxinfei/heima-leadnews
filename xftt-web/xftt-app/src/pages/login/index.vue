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
    <!-- 今日头条式红色顶栏 Logo -->
    <view class="login-brand">
      <view class="login-logo">心飞头条</view>
      <text class="login-slogan">信息创造价值</text>
    </view>
    <view class="login-form">
      <view class="login-field">
        <input v-model="form.mobile" type="number" placeholder="请输入手机号" placeholder-class="login-placeholder" />
      </view>
      <view class="login-field">
        <input v-model="form.password" password placeholder="请输入密码" placeholder-class="login-placeholder" />
      </view>
      <button class="login-btn" :disabled="isLoading" @tap="handleSubmit">
        {{ isLoading ? '登录中...' : '登录' }}
      </button>
      <text class="login-tip">登录即代表同意《用户协议》和《隐私政策》</text>
    </view>
  </view>
</template>

<style lang="scss" scoped>
.login {
  min-height: 100vh;
  background-color: #ffffff;
}
.login-brand {
  padding: 140rpx 0 80rpx;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.login-logo {
  padding: 16rpx 40rpx;
  background-color: #d43c33;
  color: #ffffff;
  font-size: 52rpx;
  font-weight: bold;
  letter-spacing: 6rpx;
  border-radius: 12rpx;
}
.login-slogan {
  margin-top: 32rpx;
  font-size: 26rpx;
  color: #999999;
  letter-spacing: 4rpx;
}
.login-form {
  width: 620rpx;
  margin: 0 auto;
}
.login-field {
  height: 100rpx;
  margin-bottom: 32rpx;
  padding: 0 28rpx;
  border-bottom: 2rpx solid #eeeeee;
  display: flex;
  align-items: center;
}
.login-placeholder {
  color: #bbbbbb;
}
.login-btn {
  margin-top: 24rpx;
  height: 92rpx;
  line-height: 92rpx;
  background-color: #d43c33;
  color: #ffffff;
  font-size: 32rpx;
  border-radius: 46rpx;
}
.login-tip {
  display: block;
  margin-top: 32rpx;
  text-align: center;
  font-size: 22rpx;
  color: #bbbbbb;
}
</style>
