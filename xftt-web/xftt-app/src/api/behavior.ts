import { request } from '../utils/request'

/** 行为上传参数：type 区分关注/点赞/收藏/阅读（对应后端 AppLikesBehaviorService 等行为服务） */
export interface BehaviorParams {
  articleId: number
  /** 0 点赞 1 取消点赞；2 收藏 3 取消收藏 */
  type: number
}

export function uploadLikesBehavior(articleId: number, type: number): Promise<void> {
  return request<void>('/v1/behavior/likes', { method: 'POST', data: { articleId, type } as unknown })
}

export function uploadReadBehavior(articleId: number): Promise<void> {
  return request<void>('/v1/behavior/read', { method: 'POST', data: { articleId } as unknown, silent: true })
}
