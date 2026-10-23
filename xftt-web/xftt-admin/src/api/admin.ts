import request from '../utils/request'
import type { ChannelItem, PageResult, SensitiveItem, UserItem } from '../types/api'

// 管理端通用 CRUD 统一走 /api/v1/admin/common（CommonDto）
// 关键字段（见后端 CommonDto / CommonServiceImpl）：
//   name        -> CommonTableEnum 枚举名：AD_CHANNEL / AD_SENSITIVE / AP_USER 等
//   model       -> 'add' 新增 / 'edit' 修改（仅 update 接口使用）
//   where / sets-> CommonWhereDto[{ filed, value, type:'eq'|'like'|'between' }]
// list 返回结构为 { list, total }，下面 toPage 映射为前端 PageResult({ rows, total })

export interface UserPageQuery { page: number; size: number; name?: string }

interface CommonWhere { filed: string; value: string; type?: string }

function toPage<T>(data: { list: T[]; total: number }): PageResult<T> {
  return { rows: data.list, total: data.total, currentPage: 0, pageSize: 0 } as PageResult<T>
}

export async function getUserPage(query: UserPageQuery): Promise<PageResult<UserItem>> {
  // 修改点：原 /user/search 无后端接口，改用通用 list（name=AP_USER）
  const data = await request.post('/api/v1/admin/common/list', { name: 'AP_USER', page: query.page, size: query.size })
  return toPage(data)
}

export async function getChannelPage(_page: number, _size: number): Promise<PageResult<ChannelItem>> {
  // 修改点：admin 频道管理需完整 AdChannel（含 ord/status），用 media 的 /api/v1/channel/channels
  // 该接口返回 List<AdChannel>（扁平数组，非 PageResult），此处包装为 {rows, total}
  const list = (await request.post('/api/v1/channel/channels')) as ChannelItem[]
  return { rows: list, total: list.length, currentPage: 0, pageSize: 0 } as PageResult<ChannelItem>
}

export async function createChannel(data: Pick<ChannelItem, 'name' | 'ord'>): Promise<void> {
  // 修改点：通用新增用 model='add' + sets
  await request.post('/api/v1/admin/common/update', {
    name: 'AD_CHANNEL',
    model: 'add',
    sets: [
      { filed: 'name', value: data.name },
      { filed: 'ord', value: String(data.ord) },
    ],
  })
}

export async function updateChannel(id: number, data: Pick<ChannelItem, 'name' | 'ord'>): Promise<void> {
  await request.post('/api/v1/admin/common/update', {
    name: 'AD_CHANNEL',
    model: 'edit',
    where: [{ filed: 'id', value: String(id), type: 'eq' }],
    sets: [
      { filed: 'name', value: data.name },
      { filed: 'ord', value: String(data.ord) },
    ],
  })
}

export async function deleteChannel(id: number): Promise<void> {
  await request.post('/api/v1/admin/common/delete', {
    name: 'AD_CHANNEL',
    where: [{ filed: 'id', value: String(id), type: 'eq' }],
  })
}

export async function getSensitivePage(page: number, size: number): Promise<PageResult<SensitiveItem>> {
  // 修改点：原 /sensitive/list 无后端接口，改用通用 list（name=AD_SENSITIVE）
  const data = await request.post('/api/v1/admin/common/list', { name: 'AD_SENSITIVE', page, size })
  return toPage(data)
}

export async function createSensitive(sensitives: string): Promise<void> {
  await request.post('/api/v1/admin/common/update', {
    name: 'AD_SENSITIVE',
    model: 'add',
    sets: [{ filed: 'sensitives', value: sensitives }],
  })
}

export async function deleteSensitive(id: number): Promise<void> {
  await request.post('/api/v1/admin/common/delete', {
    name: 'AD_SENSITIVE',
    where: [{ filed: 'id', value: String(id), type: 'eq' }],
  })
}
