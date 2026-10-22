import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useUserStore = defineStore('user', () => {
  const name = ref<string>(localStorage.getItem('xftt-wm-name') || '')
  const isAuthenticated = ref<boolean>(!!localStorage.getItem('xftt-wm-token'))

  function applyLogin(token: string, userName: string): void {
    localStorage.setItem('xftt-wm-token', token)
    localStorage.setItem('xftt-wm-name', userName)
    name.value = userName
    isAuthenticated.value = true
  }

  function logout(): void {
    localStorage.removeItem('xftt-wm-token')
    localStorage.removeItem('xftt-wm-name')
    name.value = ''
    isAuthenticated.value = false
  }

  return { name, isAuthenticated, applyLogin, logout }
})
