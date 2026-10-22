import request from '../utils/request'
import type { LoginParams, LoginResult } from '../types/api'

export function login(params: LoginParams): Promise<LoginResult> {
  return request.post('/login/in', params)
}

export function logout(): Promise<void> {
  return request.post('/login/logout')
}
