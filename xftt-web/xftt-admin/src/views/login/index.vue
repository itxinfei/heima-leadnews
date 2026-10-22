<script setup lang="ts">
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { login } from '../../api/user'
import { useUserStore } from '../../store/user'

const router = useRouter()
const userStore = useUserStore()
const isLoading = ref(false)
const form = reactive({ name: '', password: '' })

async function handleSubmit(): Promise<void> {
  if (!form.name || !form.password) {
    ElMessage.warning('请输入用户名和密码')
    return
  }
  isLoading.value = true
  try {
    const result = await login({ name: form.name, password: form.password })
    userStore.applyLogin(result.token, result.name || form.name)
    router.push('/')
  } finally {
    isLoading.value = false
  }
}
</script>

<template>
  <div class="login">
    <el-card class="login-card">
      <h2 class="login-title">心飞头条后台管理</h2>
      <el-form label-width="0">
        <el-form-item>
          <el-input v-model="form.name" placeholder="用户名" />
        </el-form-item>
        <el-form-item>
          <el-input v-model="form.password" type="password" placeholder="密码" show-password @keyup.enter="handleSubmit" />
        </el-form-item>
        <el-button type="primary" class="login-btn" :loading="isLoading" @click="handleSubmit">登录</el-button>
      </el-form>
    </el-card>
  </div>
</template>

<style scoped>
.login {
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #f0f2f5;
}
.login-card {
  width: 380px;
}
.login-title {
  text-align: center;
  margin: 0 0 24px;
}
.login-btn {
  width: 100%;
}
</style>
