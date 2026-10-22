<script setup lang="ts">
import { onMounted, reactive, ref } from 'vue'
import { getUserPage } from '../../api/admin'
import type { UserItem } from '../../types/api'

const isLoading = ref(false)
const total = ref(0)
const query = reactive({ page: 1, size: 10, name: '' })
const rows = ref<UserItem[]>([])

async function loadUsers(): Promise<void> {
  isLoading.value = true
  try {
    const result = await getUserPage({ ...query })
    rows.value = result.rows || []
    total.value = result.total || 0
  } finally {
    isLoading.value = false
  }
}

function handleSearch(): void {
  query.page = 1
  loadUsers()
}

onMounted(() => {
  loadUsers()
})
</script>

<template>
  <el-card>
    <el-form inline @submit.prevent="handleSearch">
      <el-form-item>
        <el-input v-model="query.name" placeholder="用户名" clearable />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleSearch">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table v-loading="isLoading" :data="rows" border>
      <el-table-column prop="name" label="用户名" min-width="160" />
      <el-table-column prop="mobile" label="手机号" width="160" />
      <el-table-column prop="createTime" label="注册时间" width="180" />
    </el-table>
    <el-pagination
      v-model:current-page="query.page"
      class="user-page"
      layout="total, prev, pager, next"
      :page-size="query.size"
      :total="total"
      @current-change="loadUsers"
    />
  </el-card>
</template>

<style scoped>
.user-page {
  margin-top: 16px;
  justify-content: flex-end;
}
</style>
