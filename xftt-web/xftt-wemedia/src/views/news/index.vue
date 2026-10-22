<script setup lang="ts">
import { onMounted, reactive, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { getPage } from '../../api/news'
import type { NewsItem } from '../../types/api'

const isLoading = ref(false)
const total = ref(0)
const query = reactive({ page: 1, size: 10, title: '' })
const rows = ref<NewsItem[]>([])

const STATUS_TEXT: Record<number, string> = {
  0: '草稿',
  1: '待审核',
  2: '审核失败',
  3: '已发布',
}

async function loadNews(): Promise<void> {
  isLoading.value = true
  try {
    const result = await getPage({ ...query })
    rows.value = result.rows || []
    total.value = result.total || 0
  } finally {
    isLoading.value = false
  }
}

function handleSearch(): void {
  query.page = 1
  loadNews()
}

function handleDelete(id: number): void {
  ElMessage.info(`删除功能需对接网关后启用（id=${id}）`)
}

onMounted(() => {
  loadNews()
})
</script>

<template>
  <el-card>
    <el-form inline @submit.prevent="handleSearch">
      <el-form-item>
        <el-input v-model="query.title" placeholder="文章标题" clearable />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleSearch">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table v-loading="isLoading" :data="rows" border>
      <el-table-column prop="title" label="标题" min-width="240" />
      <el-table-column label="状态" width="100">
        <template #default="{ row }">{{ STATUS_TEXT[row.status] ?? row.status }}</template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间" width="180" />
      <el-table-column label="操作" width="120">
        <template #default="{ row }">
          <el-button link type="danger" @click="handleDelete(row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      v-model:current-page="query.page"
      class="news-page"
      layout="total, prev, pager, next"
      :page-size="query.size"
      :total="total"
      @current-change="loadNews"
    />
  </el-card>
</template>

<style scoped>
.news-page {
  margin-top: 16px;
  justify-content: flex-end;
}
</style>
