<script setup lang="ts">
import { computed, onMounted, reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import type { UploadRequestOptions } from 'element-plus'
import { getChannelOptions, saveNews, uploadMaterial } from '../../api/news'
import type { ContentBlock } from '../../api/news'

const router = useRouter()
const isSaving = ref(false)
const channels = ref<Array<{ id: number; name: string }>>([])

const MAX_TITLE = 64
const form = reactive({
  title: '',
  channelId: undefined as number | undefined,
  labels: '',
})
/** 内容块编辑器：文本块与图片块混排，顺序即正文顺序 */
const blocks = ref<Array<ContentBlock & { key: number }>>([{ key: 1, type: 'text', value: '' }])
let blockKey = 1

const titleCount = computed(() => form.title.length)
const canSubmit = computed(() => {
  return !!form.title.trim() && !!form.channelId && blocks.value.some((b) => b.value.trim())
})

function addBlock(type: 'text' | 'image'): void {
  blocks.value.push({ key: ++blockKey, type, value: '' })
}

function removeBlock(index: number): void {
  blocks.value.splice(index, 1)
}

async function uploadBlockImage(block: ContentBlock & { key: number }, options: UploadRequestOptions): Promise<unknown> {
  const material = await uploadMaterial(options.file)
  block.value = material.url
  return undefined
}

async function handleSave(submit: boolean): Promise<void> {
  if (!form.title.trim()) {
    ElMessage.warning('请输入文章标题')
    return
  }
  if (!form.channelId) {
    ElMessage.warning('请选择频道')
    return
  }
  const content = blocks.value
    .map((b) => ({ type: b.type, value: b.value.trim() }))
    .filter((b) => b.value)
  if (content.length === 0) {
    ElMessage.warning('请填写正文内容')
    return
  }
  isSaving.value = true
  try {
    await saveNews(
      {
        title: form.title.trim(),
        channelId: form.channelId,
        labels: form.labels.trim() || undefined,
        content,
        type: -1,
      },
      submit,
    )
    ElMessage.success(submit ? '已提交审核' : '已存草稿')
    router.push('/news')
  } finally {
    isSaving.value = false
  }
}

onMounted(async () => {
  try {
    channels.value = await getChannelOptions()
  } catch {
    channels.value = []
  }
})
</script>

<template>
  <el-card>
    <el-form label-width="72px" class="publish-form">
      <el-form-item label="标题">
        <el-input v-model="form.title" :maxlength="MAX_TITLE" show-word-limit placeholder="请输入文章标题" />
      </el-form-item>
      <el-form-item label="频道">
        <el-select v-model="form.channelId" placeholder="请选择频道" style="width: 240px">
          <el-option v-for="c in channels" :key="c.id" :label="c.name" :value="c.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="标签">
        <el-input v-model="form.labels" placeholder="多个标签用英文逗号分隔，如：科技,互联网" />
      </el-form-item>
      <el-form-item label="正文">
        <div class="block-editor">
          <div v-for="(block, index) in blocks" :key="block.key" class="block-row">
            <el-input
              v-if="block.type === 'text'"
              v-model="block.value"
              type="textarea"
              :rows="3"
              placeholder="输入正文段落..."
            />
            <el-upload
              v-else
              :show-file-list="false"
              accept="image/gif,image/png,image/jpg,image/jpeg"
              :http-request="(options: UploadRequestOptions) => uploadBlockImage(block, options)"
            >
              <el-button size="small">选择图片</el-button>
            </el-upload>
            <el-image v-if="block.type === 'image' && block.value" :src="block.value" fit="cover" class="block-image" />
            <el-button link type="danger" size="small" class="block-remove" @click="removeBlock(index)">移除</el-button>
          </div>
          <div class="block-ops">
            <el-button size="small" @click="addBlock('text')">+ 段落</el-button>
            <el-button size="small" @click="addBlock('image')">+ 图片</el-button>
          </div>
        </div>
      </el-form-item>
      <el-form-item>
        <el-button :disabled="isSaving" @click="handleSave(false)">存草稿</el-button>
        <el-button type="danger" :disabled="!canSubmit || isSaving" @click="handleSave(true)">提交审核</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<style scoped>
.publish-form {
  max-width: 760px;
}
.block-editor {
  width: 100%;
}
.block-row {
  display: flex;
  align-items: flex-start;
  gap: 8px;
  margin-bottom: 12px;
}
.block-row > .el-input,
.block-row > .el-textarea {
  flex: 1;
}
.block-image {
  width: 120px;
  height: 80px;
  border-radius: 4px;
}
.block-remove {
  margin-top: 6px;
}
.block-ops {
  margin-top: 8px;
}
</style>
