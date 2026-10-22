<script setup lang="ts">
import { useRoute, useRouter } from 'vue-router'
import { useUserStore } from '../../store/user'

const router = useRouter()
const route = useRoute()
const userStore = useUserStore()

function handleLogout(): void {
  userStore.logout()
  router.push('/login')
}
</script>

<template>
  <el-container class="layout">
    <el-aside width="200px" class="layout-aside">
      <div class="layout-logo">心飞头条<i class="layout-logo-badge">自媒体</i></div>
      <el-menu :default-active="route.path" router>
        <el-menu-item index="/news">内容管理</el-menu-item>
        <el-menu-item index="/material">素材管理</el-menu-item>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header class="layout-header">
        <span>{{ userStore.name || '自媒体用户' }}</span>
        <el-button link type="danger" @click="handleLogout">退出登录</el-button>
      </el-header>
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<style scoped>
.layout {
  height: 100vh;
}
.layout-aside {
  border-right: 1px solid #e6e6e6;
  background-color: #fff;
}
.layout-logo {
  height: 60px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  font-weight: bold;
  color: #ED4040;
}
.layout-logo-badge {
  font-style: normal;
  font-size: 12px;
  font-weight: normal;
  color: #fff;
  background-color: #ED4040;
  border-radius: 3px;
  padding: 1px 6px;
}
.layout-header {
  display: flex;
  align-items: center;
  justify-content: space-between;
  border-bottom: 1px solid #e6e6e6;
}
</style>
