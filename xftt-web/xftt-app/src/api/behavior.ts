import { request } from '../utils/request'

/**
 * 行为上传：路径与参数对齐 xftt-behavior 服务 BehaviorController 现有约定
 * 点赞：POST /api/v1/behavior/like_behavior，operation 0 点赞 / 1 取消点赞
 * 阅读：POST /api/v1/behavior/read_behavior
 */
export function uploadLikesBehavior(entryId: number, operation: 0 | 1): Promise<void> {
  return request<void>('/behavior/like_behavior', {
    method: 'POST',
    data: { equipmentId: 0, entryId, type: 0, operation } as unknown,
  })
}

export function uploadReadBehavior(articleId: number): Promise<void> {
  return request<void>('/behavior/read_behavior', {
    method: 'POST',
    data: { equipmentId: 0, articleId, count: 1, readDuration: 0, percentage: 100, loadDuration: 0 } as unknown,
    silent: true,
  })
}

/** 详情页互动状态回显（BE-F02）：islike / isunlike / iscollection / isfollow，失败静默 */
export interface ArticleBehaviorState {
  islike?: boolean
  isunlike?: boolean
  iscollection?: boolean
  isfollow?: boolean
}

export function loadArticleBehavior(articleId: number, authorId?: number): Promise<ArticleBehaviorState> {
  return request<ArticleBehaviorState>('/article/load_article_behavior', {
    method: 'POST',
    data: { equipmentId: 0, articleId, authorId } as unknown,
    silent: true,
  })
}
