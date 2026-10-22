import { request } from '../utils/request'
import type { LoginParams, LoginResult } from '../types/api'

export function login(params: LoginParams): Promise<LoginResult> {
  return request<LoginResult>('/v1/user/login', { method: 'POST', data: params })
}
