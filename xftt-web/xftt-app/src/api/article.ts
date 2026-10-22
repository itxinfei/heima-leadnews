import { request } from '../utils/request'
import type { ArticleDetail, ArticleItem, PageResult } from '../types/api'

export interface ArticlePageQuery {
  page: number
  size: number
  channelId?: number
}

export function getPage(query: ArticlePageQuery): Promise<PageResult<ArticleItem>> {
  return request<PageResult<ArticleItem>>('/v1/article/load', { data: query as unknown })
}

export function getDetail(id: number): Promise<ArticleDetail> {
  return request<ArticleDetail>(`/v1/article/${id}`)
}
