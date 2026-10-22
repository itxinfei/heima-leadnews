import request from '../utils/request'
import type { ChannelItem, PageResult, SensitiveItem, UserItem } from '../types/api'

export interface UserPageQuery {
  page: number
  size: number
  name?: string
}

export function getUserPage(query: UserPageQuery): Promise<PageResult<UserItem>> {
  return request.post('/user/search', query)
}

export function getChannelPage(page: number, size: number): Promise<PageResult<ChannelItem>> {
  return request.get('/channel/list', { params: { page, size } })
}

export function createChannel(data: Pick<ChannelItem, 'name' | 'ord'>): Promise<void> {
  return request.post('/channel/create', data)
}

export function updateChannel(id: number, data: Pick<ChannelItem, 'name' | 'ord'>): Promise<void> {
  return request.put(`/channel/${id}`, data)
}

export function deleteChannel(id: number): Promise<void> {
  return request.delete(`/channel/${id}`)
}

export function getSensitivePage(page: number, size: number): Promise<PageResult<SensitiveItem>> {
  return request.get('/sensitive/list', { params: { page, size } })
}

export function createSensitive(sensitives: string): Promise<void> {
  return request.post('/sensitive/create', { sensitives })
}

export function deleteSensitive(id: number): Promise<void> {
  return request.delete(`/sensitive/${id}`)
}
