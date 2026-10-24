import axios from 'axios'
import { ElMessage } from 'element-plus'
import type { ApiResponse } from '../types/api'

// baseURL 置空：所有请求路径在 api/*.ts 中写完整后端契约路径，避免 /api 前缀与网关/服务路径冲突
const instance = axios.create({ baseURL: '', timeout: 10000 })

instance.interceptors.request.use((config) => {
  const token = localStorage.getItem('xftt-admin-token')
  if (token) {
    config.headers.Authorization = token
    // 后端 AdminTokenFilter 读取小写 token 头，补齐以对齐（否则 /api/v1/admin/* 返回 code=52）
    config.headers.token = token
  }
  return config
})

instance.interceptors.response.use(
  (response) => {
    const body = response.data as ApiResponse<unknown>
    if (body.code === 0) return body.data as never
    ElMessage.error(body.errorMessage || '请求失败')
    return Promise.reject(new Error(body.errorMessage || '请求失败'))
  },
  (error) => {
    ElMessage.error('网络异常')
    return Promise.reject(error)
  },
)

export default instance
