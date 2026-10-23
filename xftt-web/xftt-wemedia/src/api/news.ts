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

export interface MaterialPage { curPage: number; size: number; list: MaterialItem[]; total: number }

export async function getMaterialPage(page: number, size: number): Promise<MaterialPage> {
  // 修改点：原 GET /material/list 改为 POST /api/v1/media/material/list
  const data = await request.post('/api/v1/media/material/list', { page, size })
  return data as unknown as MaterialPage
}

/** 上传图片素材（WM-F06）：multipart 字段名 file，后端写 MinIO 并落 wm_material */
export async function uploadMaterial(file: File): Promise<MaterialItem> {
  const form = new FormData()
  form.append('file', file)
  const data = await request.post('/api/v1/media/material/upload_picture', form, {
    headers: { 'Content-Type': 'multipart/form-data' },
  })
  return data as unknown as MaterialItem
}

/** 删除图片素材（WM-F06）：后端校验引用后删 MinIO 与记录 */
export function deleteMaterial(id: number): Promise<void> {
  return request.post('/api/v1/media/material/del_picture', { id })
}

/** 收藏 / 取消收藏素材 */
export function collectMaterial(id: number, collect: boolean): Promise<void> {
  return request.post(`/api/v1/media/material/${collect ? 'collect' : 'cancel_collect'}`, { id })
}

/** 频道下拉（媒体端） */
export async function getChannelOptions(): Promise<Array<{ id: number; name: string }>> {
  const data = await request.post('/api/v1/channel/channels')
  return (data as unknown as Array<{ id: number; name: string }>) || []
}

/** 文章内容块：与后端 NewsServiceImpl.extractUrlInfo 约定一致，图片 value 需带完整 URL 前缀 */
export interface ContentBlock {
  type: 'text' | 'image'
  value: string
}

/** 保存文章（WM-F05）：submit=true 提交审核，false 存草稿 */
export function saveNews(
  params: {
    title: string
    channelId: number
    labels?: string
    content: ContentBlock[]
    type: number
  },
  submit: boolean,
): Promise<void> {
  const payload = { ...params, content: JSON.stringify(params.content) }
  return request.post(submit ? '/api/v1/media/news/submit' : '/api/v1/media/news/save_draft', payload)
}
