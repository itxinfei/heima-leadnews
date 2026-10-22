import request from '../utils/request'
import type { MaterialItem, NewsItem, PageResult } from '../types/api'

export interface NewsPageQuery {
  page: number
  size: number
  title?: string
}

export function getPage(query: NewsPageQuery): Promise<PageResult<NewsItem>> {
  return request.post('/news/search', query)
}

export function deleteNews(id: number): Promise<void> {
  return request.delete(`/news/${id}`)
}

export function getMaterialPage(page: number, size: number): Promise<PageResult<MaterialItem>> {
  return request.get('/material/list', { params: { page, size } })
}
