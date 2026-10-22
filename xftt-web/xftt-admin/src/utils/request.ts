import axios from 'axios'
import { ElMessage } from 'element-plus'
import type { ApiResponse } from '../types/api'

const instance = axios.create({ baseURL: '/api', timeout: 10000 })

instance.interceptors.request.use((config) => {
  const token = localStorage.getItem('xftt-admin-token')
  if (token) config.headers.Authorization = token
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
