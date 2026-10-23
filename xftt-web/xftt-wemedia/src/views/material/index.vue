<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import type { UploadRequestOptions } from 'element-plus'
import { collectMaterial, deleteMaterial, getMaterialPage, uploadMaterial } from '../../api/news'
import type { MaterialItem } from '../../types/api'

const isLoading = ref(false)
const isUploading = ref(false)
const materials = ref<MaterialItem[]>([])
const total = ref(0)
const page = ref(1)
const size = 20
const onlyCollect = ref(false)

async function loadMaterials(): Promise<void> {
  isLoading.value = true
  try {
    const result = await getMaterialPage(page.value, size)
    const rows = (result.list || []) as Array<MaterialItem & { isCollection?: number }>
    materials.value = onlyCollect.value ? rows.filter((m) => m.isCollection === 1) : rows
    total.value = result.total || 0
  } finally {
    isLoading.value = false
  }
}

async function handleUpload(options: UploadRequestOptions): Promise<unknown> {
  isUploading.value = true
  try {
    await uploadMaterial(options.file)
    ElMessage.success('上传成功')
    page.value = 1
    loadMaterials()
  } finally {
    isUploading.value = false
  }
  return undefined
}

function beforeUpload(file: File): boolean {
  const ok = ['image/gif', 'image/png', 'image/jpg', 'image/jpeg'].includes(file.type)
  if (!ok) ElMessage.warning('仅支持 jpg/png/gif 图片')
  if (file.size > 5 * 1024 * 1024) {
    ElMessage.warning('图片不能超过 5MB')
    return false
  }
  return ok
}

async function handleDelete(item: MaterialItem): Promise<void> {
  await ElMessageBox.confirm('删除后不可恢复，确认删除该素材？', '删除素材', { type: 'warning' })
  await deleteMaterial(item.id)
  ElMessage.success('已删除')
  loadMaterials()
}

async function handleCollect(item: MaterialItem & { isCollection?: number }): Promise<void> {
  const collect = item.isCollection !== 1
  await collectMaterial(item.id, collect)
  item.isCollection = collect ? 1 : 0
}

function handlePageChange(next: number): void {
  page.value = next
  loadMaterials()
}

onMounted(() => {
  loadMaterials()
})
</script>

<template>
  <el-card>
    <div class="material-toolbar">
      <el-upload
        :show-file-list="false"
        :http-request="handleUpload"
        :before-upload="beforeUpload"
        accept="image/gif,image/png,image/jpg,image/jpeg"
      >
        <el-button type="danger" :loading="isUploading">上传图片</el-button>
      </el-upload>
      <el-checkbox v-model="onlyCollect" @change="loadMaterials">只看收藏</el-checkbox>
    </div>

    <div v-loading="isLoading" class="material-grid">
      <el-empty v-if="!isLoading && materials.length === 0" description="暂无素材" />
      <div v-for="item in materials" :key="item.id" class="material-card">
        <el-image class="material-item" :src="item.url" :preview-src-list="[item.url]" fit="cover" />
        <div class="material-actions">
          <el-button link type="danger" size="small" @click="handleDelete(item)">删除</el-button>
          <el-button link size="small" @click="handleCollect(item)">收藏</el-button>
        </div>
      </div>
    </div>

    <el-pagination
      v-if="total > size"
      class="material-pagination"
      layout="prev, pager, next"
      :total="total"
      :page-size="size"
      :current-page="page"
      @current-change="handlePageChange"
    />
  </el-card>
</template>

<style scoped>
.material-toolbar {
  display: flex;
  align-items: center;
  gap: 16px;
  margin-bottom: 16px;
}
.material-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(160px, 1fr));
  gap: 16px;
  min-height: 200px;
}
.material-card {
  position: relative;
}
.material-item {
  width: 100%;
  height: 120px;
  border-radius: 4px;
}
.material-actions {
  display: flex;
  justify-content: center;
  gap: 8px;
  margin-top: 4px;
}
.material-pagination {
  margin-top: 16px;
  justify-content: flex-end;
}
</style>
