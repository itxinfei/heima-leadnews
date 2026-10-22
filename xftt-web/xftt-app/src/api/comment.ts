import { request } from '../utils/request'

/** 评论记录 */
export interface CommentItem {
  id: number
  articleId: number
  content: string
  authorName: string
  createTime: string
  likes?: number
}

/** 提交评论参数 */
export interface CreateCommentParams {
  articleId: number
  content: string
}

export function getCommentList(articleId: number, page = 1, size = 20): Promise<CommentItem[]> {
  return request<CommentItem[]>('/v1/comment/list', { data: { articleId, page, size } })
}

export function createComment(params: CreateCommentParams): Promise<void> {
  return request<void>('/v1/comment/create', { method: 'POST', data: params })
}

/** 文章互动：点赞/收藏/取消，type 区分行为 */
export function likeComment(commentId: number): Promise<void> {
  return request<void>('/v1/comment/like', { method: 'POST', data: { commentId } })
}
