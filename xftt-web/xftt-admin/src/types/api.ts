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

/** 自媒体文章状态（wm_news.status，对应后端 WmMediaConstans） */
export enum WmNewsStatusEnum {
  Draft = 0,
  Submitted = 1,
  Rejected = 2,
  ManualReview = 3,
  Authed = 8,
  Published = 9,
}

/** 审核工作台列表项（wm_news 子集） */
export interface ReviewItem {
  id: number
  userId: number
  title: string
  type: number
  labels: string
  status: WmNewsStatusEnum
  publishTime: string | null
  reason: string | null
  images: string | null
  channelId: number
  submitedTime: string | null
  createdTime: string | null
  /** 正文：JSON 块数组 [{type:'text'|'image', value}]，详情接口返回 */
  content?: string
  articleId?: number | null
}

/** 审核列表查询参数 */
export interface ReviewPageQuery {
  page: number
  size: number
  statusList: WmNewsStatusEnum[]
  keyWord?: string
}
