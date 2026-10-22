<script setup lang="ts">
import { onMounted, reactive, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { createChannel, deleteChannel, getChannelPage, updateChannel } from '../../api/admin'
import type { ChannelItem } from '../../types/api'

const isLoading = ref(false)
const rows = ref<ChannelItem[]>([])
const dialogVisible = ref(false)
const isEdit = ref(false)
const currentId = ref(0)
const form = reactive({ name: '', ord: 0 })

async function loadChannels(): Promise<void> {
  isLoading.value = true
  try {
    const result = await getChannelPage(1, 100)
    rows.value = result.rows || []
  } finally {
    isLoading.value = false
  }
}

function openCreate(): void {
  isEdit.value = false
  form.name = ''
  form.ord = 0
  dialogVisible.value = true
}

function openEdit(row: ChannelItem): void {
  isEdit.value = true
  currentId.value = row.id
  form.name = row.name
  form.ord = row.ord
  dialogVisible.value = true
}

async function handleSave(): Promise<void> {
  if (!form.name) {
    ElMessage.warning('请输入频道名称')
    return
  }
  if (isEdit.value) {
    await updateChannel(currentId.value, { name: form.name, ord: form.ord })
  } else {
    await createChannel({ name: form.name, ord: form.ord })
  }
  ElMessage.success('保存成功')
  dialogVisible.value = false
  loadChannels()
}

async function handleDelete(id: number): Promise<void> {
  await deleteChannel(id)
  ElMessage.success('删除成功')
  loadChannels()
}

onMounted(() => {
  loadChannels()
})
</script>

<template>
  <el-card>
    <el-button type="primary" class="channel-add" @click="openCreate">新增频道</el-button>
    <el-table v-loading="isLoading" :data="rows" border>
      <el-table-column prop="name" label="频道名称" min-width="160" />
      <el-table-column prop="ord" label="排序" width="100" />
      <el-table-column label="操作" width="160">
        <template #default="{ row }">
          <el-button link type="primary" @click="openEdit(row)">编辑</el-button>
          <el-button link type="danger" @click="handleDelete(row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog v-model="dialogVisible" :title="isEdit ? '编辑频道' : '新增频道'" width="400px">
      <el-form label-width="80px">
        <el-form-item label="名称">
          <el-input v-model="form.name" />
        </el-form-item>
        <el-form-item label="排序">
          <el-input-number v-model="form.ord" :min="0" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSave">保存</el-button>
      </template>
    </el-dialog>
  </el-card>
</template>

<style scoped>
.channel-add {
  margin-bottom: 16px;
}
</style>
