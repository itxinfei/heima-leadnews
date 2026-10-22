import { defineStore } from 'pinia'
import { ref } from 'vue'
import { clearToken, getToken, setToken } from '../utils/request'

export const useUserStore = defineStore('user', () => {
  const userId = ref<number>(0)
  const nickName = ref<string>('')
  const isAuthenticated = ref<boolean>(!!getToken())

  function applyLogin(token: string, id: number, name?: string): void {
    setToken(token)
    userId.value = id
    nickName.value = name || ''
    isAuthenticated.value = true
  }

  function logout(): void {
    clearToken()
    userId.value = 0
    nickName.value = ''
    isAuthenticated.value = false
  }

  return { userId, nickName, isAuthenticated, applyLogin, logout }
})
