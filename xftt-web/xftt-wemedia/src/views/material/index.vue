<script setup lang="ts">
import { onMounted, ref } from 'vue'
import { getMaterialPage } from '../../api/news'
import type { MaterialItem } from '../../types/api'

const isLoading = ref(false)
const materials = ref<MaterialItem[]>([])

async function loadMaterials(): Promise<void> {
  isLoading.value = true
  try {
    const result = await getMaterialPage(1, 50)
    materials.value = result.rows || []
  } finally {
    isLoading.value = false
  }
}

onMounted(() => {
  loadMaterials()
})
</script>

<template>
  <el-card>
    <div v-loading="isLoading" class="material-grid">
      <el-empty v-if="!isLoading && materials.length === 0" description="暂无素材" />
      <el-image
        v-for="item in materials"
        :key="item.id"
        class="material-item"
        :src="item.url"
        :preview-src-list="[item.url]"
        fit="cover"
      />
    </div>
  </el-card>
</template>

<style scoped>
.material-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(160px, 1fr));
  gap: 16px;
  min-height: 200px;
}
.material-item {
  width: 100%;
  height: 120px;
  border-radius: 4px;
}
</style>
