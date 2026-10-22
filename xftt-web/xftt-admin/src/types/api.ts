/** 后端统一响应结构 */
export interface ApiResponse<T> {
  code: number
  errorMessage: string | null
  data: T
}

export interface PageResult<T> {
  currentPage: number
  pageSize: number
  total: number
  /** 后端分页协议实际返回的行数据 */
  rows: T[]
}

export interface LoginParams {
  name: string
  password: string
}

export interface LoginResult {
  token: string
  userId: number
  name?: string
}

export interface UserItem {
  id: number
  name: string
  mobile: string
  image?: string | null
  createTime: string
}

export interface ChannelItem {
  id: number
  name: string
  ord: number
  status: boolean
}

export interface SensitiveItem {
  id: number
  sensitives: string
  createdTime: string
}
