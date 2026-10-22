<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { createSensitive, deleteSensitive, getSensitivePage } from '../../api/admin'
import type { SensitiveItem } from '../../types/api'

const isLoading = ref(false)
const rows = ref<SensitiveItem[]>([])
const inputVisible = ref(false)
const newWord = ref('')

async function loadSensitive(): Promise<void> {
  isLoading.value = true
  try {
    const result = await getSensitivePage(1, 100)
    rows.value = result.rows || []
  } finally {
    isLoading.value = false
  }
}

async function handleCreate(): Promise<void> {
  if (!newWord.value) {
    ElMessage.warning('请输入敏感词')
    return
  }
  await createSensitive(newWord.value)
  ElMessage.success('添加成功')
  newWord.value = ''
  inputVisible.value = false
  loadSensitive()
}

async function handleDelete(id: number): Promise<void> {
  await deleteSensitive(id)
  ElMessage.success('删除成功')
  loadSensitive()
}

onMounted(() => {
  loadSensitive()
})
</script>

<template>
  <el-card>
    <div class="sensitive-toolbar">
      <el-input v-if="inputVisible" v-model="newWord" class="sensitive-input" placeholder="敏感词" @keyup.enter="handleCreate" />
      <el-button v-if="inputVisible" type="primary" @click="handleCreate">保存</el-button>
      <el-button v-else type="primary" @click="inputVisible = true">新增敏感词</el-button>
    </div>
    <el-table v-loading="isLoading" :data="rows" border>
      <el-table-column prop="sensitives" label="敏感词" min-width="200" />
      <el-table-column prop="createdTime" label="创建时间" width="180" />
      <el-table-column label="操作" width="120">
        <template #default="{ row }">
          <el-button link type="danger" @click="handleDelete(row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>

<style scoped>
.sensitive-toolbar {
  display: flex;
  gap: 8px;
  margin-bottom: 16px;
}
.sensitive-input {
  width: 240px;
}
</style>
