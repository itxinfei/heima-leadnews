import request from '../utils/request'
import type { MaterialItem, NewsItem, PageResult } from '../types/api'

export interface NewsPageQuery { page: number; size: number; title?: string }

export function getPage(query: NewsPageQuery): Promise<PageResult<NewsItem>> {
  // 修改点：原 /news/search 无对应后端，改为 /api/v1/media/news/list
  return request.post('/api/v1/media/news/list', query)
}

export function deleteNews(id: number): Promise<void> {
  // 修改点：原 DELETE /news/{id} 改为 POST /api/v1/media/news/del_news
  return request.post('/api/v1/media/news/del_news', { id })
}

export function getMaterialPage(page: number, size: number): Promise<PageResult<MaterialItem>> {
  // 修改点：原 GET /material/list 改为 POST /api/v1/media/material/list
  return request.post('/api/v1/media/material/list', { page, size })
}
