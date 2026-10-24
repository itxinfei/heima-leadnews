import request from '../utils/request'
import type { ReviewItem, ReviewPageQuery } from '../types/api'

// 人工审核工作台（后端 /api/v1/admin/review，ArticleReviewController）
// statusList：待审核=[3 需人工]、已通过=[8 审核通过待发布, 9 已发布]、已驳回=[2 审核失败]

interface ReviewPageData {
  list: ReviewItem[]
  total: number
}

export async function getReviewPage(query: ReviewPageQuery): Promise<ReviewPageData> {
  const data = await request.post('/api/v1/admin/review/list', query)
  return data as unknown as ReviewPageData
}

export async function getReviewDetail(id: number): Promise<ReviewItem> {
  const data = await request.post('/api/v1/admin/review/detail', { id })
  return data as unknown as ReviewItem
}

export async function passReview(id: number): Promise<void> {
  await request.post('/api/v1/admin/review/pass', { id })
}

export async function rejectReview(id: number, reason: string): Promise<void> {
  await request.post('/api/v1/admin/review/reject', { id, reason })
}
