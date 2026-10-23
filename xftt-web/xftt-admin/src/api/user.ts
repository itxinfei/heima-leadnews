import request from '../utils/request'
import type { LoginParams, LoginResult } from '../types/api'

export function login(params: LoginParams): Promise<LoginResult> {
  return request.post('/login/in', params)
}

// 登出纯前端处理：清空本地 token（后端无 /login/logout 接口）
// 视图实际调用 useUserStore().logout() 完成状态与本地存储清理；此处保留无请求的兜底实现
export function logout(): Promise<void> {
  localStorage.removeItem('xftt-admin-token')
  localStorage.removeItem('xftt-admin-name')
  return Promise.resolve()
}
