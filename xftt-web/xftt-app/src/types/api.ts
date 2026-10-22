/** 后端统一响应结构，字段对应网关真实协议 */
export interface ApiResponse<T> {
  code: number
  errorMessage: string | null
  data: T
}

/** 分页结果 */
export interface PageResult<T> {
  currentPage: number
  pageSize: number
  total: number
  list: T[]
}

/** 文章列表记录 */
export interface ArticleItem {
  id: number
  title: string
  image?: string | null
  likes?: number
  views?: number
  publishTime?: string
  authorName?: string
}

/** 文章详情 */
export interface ArticleDetail extends ArticleItem {
  content: string
}

/** 登录参数 */
export interface LoginParams {
  mobile: string
  password: string
}

/** 登录结果 */
export interface LoginResult {
  token: string
  userId: number
  nickName?: string
}
