const BASE_URL = import.meta.env.VITE_API_BASE_URL || '/api'
const TOKEN_KEY = 'xftt-token'

export function getToken(): string {
  return uni.getStorageSync(TOKEN_KEY) || ''
}

export function setToken(token: string): void {
  uni.setStorageSync(TOKEN_KEY, token)
}

export function clearToken(): void {
  uni.removeStorageSync(TOKEN_KEY)
}

/** 统一请求封装：解包 ApiResponse，业务失败时提示并 reject */
export function request<T>(url: string, options: { method?: 'GET' | 'POST'; data?: unknown } = {}): Promise<T> {
  const token = getToken()
  return new Promise<T>((resolve, reject) => {
    uni.request({
      url: BASE_URL + url,
      method: options.method || 'GET',
      data: options.data as Record<string, unknown> | undefined,
      header: token ? { Authorization: token } : undefined,
      success: (res) => {
        const body = res.data as ApiResponse<T>
        if (body && body.code === 0) {
          resolve(body.data)
        } else {
          uni.showToast({ title: body?.errorMessage || '请求失败', icon: 'none' })
          reject(new Error(body?.errorMessage || '请求失败'))
        }
      },
      fail: (err) => {
        uni.showToast({ title: '网络异常', icon: 'none' })
        reject(err)
      },
    })
  })
}
