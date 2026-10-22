import { request } from '../utils/request'
import type { ArticleItem } from '../types/api'

/** 搜索历史记录（对应 ApUserSearch） */
export interface UserSearchItem {
  id: number
  keyword: string
  createdTime: string
}

/** 联想词（对应 ApAssociateWords） */
export interface AssociateWord {
  id: number
  associateWords: string
}

/** 热词（对应 ApHotWords） */
export interface HotWord {
  id: number
  hotWords: string
  hotDate: string
}

/** 搜索请求公共参数，对齐 UserSearchDto */
export interface UserSearchQuery {
  searchWords?: string
  pageNum?: number
  pageSize?: number
  hotDate?: string
}

export function loadSearchHistory(): Promise<UserSearchItem[]> {
  return request<UserSearchItem[]>('/article/search/load_search_history', {
    method: 'POST',
    data: { pageNum: 1, pageSize: 20 } as unknown,
    silent: true,
  })
}

export function clearSearchHistory(): Promise<number> {
  return request<number>('/article/search/clear_search', { method: 'POST', data: {}, silent: true })
}

export function loadHotKeywords(): Promise<HotWord[]> {
  return request<HotWord[]>('/article/search/load_hot_keywords', { method: 'POST', data: {}, silent: true })
}

export function loadAssociateWords(keyword: string): Promise<AssociateWord[]> {
  return request<AssociateWord[]>('/article/search/associate_search', {
    method: 'POST',
    data: { searchWords: keyword, pageSize: 10 } as unknown,
    silent: true,
  })
}

/** ES 关键词搜索：fromIndex=0 时后端自动写入搜索历史 */
export function searchArticles(keyword: string, pageNum = 1, pageSize = 10): Promise<ArticleItem[]> {
  return request<ArticleItem[]>('/article/search/article_search', {
    method: 'POST',
    data: { searchWords: keyword, pageNum, pageSize } as unknown,
  })
}
