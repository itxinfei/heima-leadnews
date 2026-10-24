<script setup lang="ts">
import { onMounted, reactive, ref, watch } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getReviewDetail, getReviewPage, passReview, rejectReview } from '../../api/review'
import { FILE_SERVER_URL } from '../../config'
import { WmNewsStatusEnum } from '../../types/api'
import type { ReviewItem } from '../../types/api'

type TabKey = 'pending' | 'passed' | 'rejected'

const tabStatusMap: Record<TabKey, WmNewsStatusEnum[]> = {
  pending: [WmNewsStatusEnum.ManualReview],
  passed: [WmNewsStatusEnum.Authed, WmNewsStatusEnum.Published],
  rejected: [WmNewsStatusEnum.Rejected],
}

const isLoading = ref(false)
const total = ref(0)
const activeTab = ref<TabKey>('pending')
const query = reactive({ page: 1, size: 10, keyWord: '' })
const rows = ref<ReviewItem[]>([])

async function loadList(): Promise<void> {
  isLoading.value = true
  try {
    const result = await getReviewPage({
      page: query.page,
      size: query.size,
      keyWord: query.keyWord.trim() || undefined,
      statusList: tabStatusMap[activeTab.value],
    })
    rows.value = result.list || []
    total.value = result.total || 0
  } finally {
    isLoading.value = false
  }
}

function handleSearch(): void {
  query.page = 1
  loadList()
}

// 切换 Tab 时重置到第一页并重新查询
watch(activeTab, () => {
  query.page = 1
  loadList()
})

// —— 详情抽屉 ——
const detailVisible = ref(false)
const detailLoading = ref(false)
const detail = ref<ReviewItem | null>(null)

interface ContentBlock {
  type: string
  value: string
}

function parseContent(content?: string): ContentBlock[] {
  if (!content) return []
  try {
    const arr = JSON.parse(content)
    return Array.isArray(arr) ? (arr as ContentBlock[]) : []
  } catch {
    return []
  }
}

function coverImages(images?: string | null): string[] {
  if (!images) return []
  return images.split(',').filter(Boolean).map((id) => FILE_SERVER_URL + id)
}

async function openDetail(row: ReviewItem): Promise<void> {
  detailVisible.value = true
  detailLoading.value = true
  detail.value = null
  try {
    detail.value = await getReviewDetail(row.id)
  } finally {
    detailLoading.value = false
  }
}

// —— 审核通过 ——
async function handlePass(row: ReviewItem): Promise<void> {
  try {
    await ElMessageBox.confirm(`确认通过文章《${row.title}》？`, '审核通过', { type: 'warning' })
  } catch {
    return // 用户取消
  }
  try {
    await passReview(row.id)
    ElMessage.success('已通过')
    loadList()
  } catch {
    /* 请求层已提示错误 */
  }
}

// —— 审核驳回 ——
const rejectVisible = ref(false)
const rejectTarget = ref<ReviewItem | null>(null)
const rejectReason = ref('')
const rejectSubmitting = ref(false)

function openReject(row: ReviewItem): void {
  rejectTarget.value = row
  rejectReason.value = ''
  rejectVisible.value = true
}

async function submitReject(): Promise<void> {
  if (!rejectReason.value.trim()) {
    ElMessage.warning('请填写驳回理由')
    return
  }
  rejectSubmitting.value = true
  try {
    await rejectReview(rejectTarget.value!.id, rejectReason.value.trim())
    ElMessage.success('已驳回并通知作者')
    rejectVisible.value = false
    loadList()
  } catch {
    /* 请求层已提示错误 */
  } finally {
    rejectSubmitting.value = false
  }
}

// —— 展示辅助 ——
function statusTag(status: WmNewsStatusEnum): { type: 'info' | 'success' | 'warning' | 'danger'; text: string } {
  switch (status) {
    case WmNewsStatusEnum.ManualReview:
      return { type: 'warning', text: '待审核' }
    case WmNewsStatusEnum.Authed:
      return { type: 'success', text: '审核通过(待发布)' }
    case WmNewsStatusEnum.Published:
      return { type: 'success', text: '已发布' }
    case WmNewsStatusEnum.Rejected:
      return { type: 'danger', text: '已驳回' }
    case WmNewsStatusEnum.Submitted:
      return { type: 'info', text: '已提交' }
    default:
      return { type: 'info', text: '草稿' }
  }
}

function typeText(t: number): string {
  if (t === 1) return '单图'
  if (t === 3) return '多图'
  return '无图'
}

onMounted(loadList)
</script>

<template>
  <el-card>
    <el-tabs v-model="activeTab">
      <el-tab-pane label="待审核" name="pending" />
      <el-tab-pane label="已通过" name="passed" />
      <el-tab-pane label="已驳回" name="rejected" />
    </el-tabs>
    <el-form inline @submit.prevent="handleSearch">
      <el-form-item>
        <el-input v-model="query.keyWord" placeholder="文章标题" clearable />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleSearch">查询</el-button>
      </el-form-item>
    </el-form>
    <el-table v-loading="isLoading" :data="rows" border>
      <el-table-column prop="title" label="标题" min-width="220" show-overflow-tooltip />
      <el-table-column label="类型" width="90">
        <template #default="{ row }">{{ typeText(row.type) }}</template>
      </el-table-column>
      <el-table-column prop="labels" label="标签" min-width="120" show-overflow-tooltip />
      <el-table-column prop="userId" label="作者ID" width="100" />
      <el-table-column prop="submitedTime" label="提交时间" width="180" />
      <el-table-column label="状态" width="150">
        <template #default="{ row }">
          <el-tag :type="statusTag(row.status).type">{{ statusTag(row.status).text }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="220" fixed="right">
        <template #default="{ row }">
          <el-button link type="primary" @click="openDetail(row)">详情</el-button>
          <template v-if="activeTab === 'pending'">
            <el-button link type="success" @click="handlePass(row)">通过</el-button>
            <el-button link type="danger" @click="openReject(row)">驳回</el-button>
          </template>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      v-model:current-page="query.page"
      class="review-page"
      layout="total, prev, pager, next"
      :page-size="query.size"
      :total="total"
      @current-change="loadList"
    />
  </el-card>

  <el-drawer v-model="detailVisible" title="文章详情" size="40%">
    <div v-loading="detailLoading">
      <template v-if="detail">
        <h3 class="review-detail-title">{{ detail.title }}</h3>
        <el-descriptions :column="1" border>
          <el-descriptions-item label="状态">{{ statusTag(detail.status).text }}</el-descriptions-item>
          <el-descriptions-item label="标签">{{ detail.labels || '-' }}</el-descriptions-item>
          <el-descriptions-item label="作者ID">{{ detail.userId }}</el-descriptions-item>
          <el-descriptions-item label="提交时间">{{ detail.submitedTime || '-' }}</el-descriptions-item>
          <el-descriptions-item label="定时发布">{{ detail.publishTime || '不定时' }}</el-descriptions-item>
          <el-descriptions-item label="审核理由">{{ detail.reason || '-' }}</el-descriptions-item>
        </el-descriptions>
        <div v-if="coverImages(detail.images).length" class="review-covers">
          <el-image
            v-for="(url, i) in coverImages(detail.images)"
            :key="i"
            :src="url"
            :preview-src-list="coverImages(detail.images)"
            fit="cover"
          />
        </div>
        <div class="review-content">
          <template v-for="(b, i) in parseContent(detail.content)" :key="i">
            <p v-if="b.type === 'text'">{{ b.value }}</p>
            <el-image v-else-if="b.type === 'image'" :src="b.value" :preview-src-list="[b.value]" fit="contain" />
          </template>
        </div>
      </template>
    </div>
  </el-drawer>

  <el-dialog v-model="rejectVisible" title="驳回文章" width="480px">
    <el-input
      v-model="rejectReason"
      type="textarea"
      :rows="4"
      maxlength="200"
      placeholder="请填写驳回理由（将通知作者）"
      show-word-limit
    />
    <template #footer>
      <el-button @click="rejectVisible = false">取消</el-button>
      <el-button type="danger" :loading="rejectSubmitting" @click="submitReject">确认驳回</el-button>
    </template>
  </el-dialog>
</template>

<style scoped>
.review-page {
  margin-top: 16px;
  justify-content: flex-end;
}
.review-detail-title {
  margin: 0 0 12px;
}
.review-covers {
  display: flex;
  gap: 8px;
  flex-wrap: wrap;
  margin: 12px 0;
}
.review-covers :deep(.el-image) {
  width: 120px;
  height: 80px;
  border-radius: 4px;
}
.review-content :deep(.el-image) {
  width: 100%;
  margin: 8px 0;
}
</style>
