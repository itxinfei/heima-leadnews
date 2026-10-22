import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useUserStore = defineStore('user', () => {
  const name = ref<string>(localStorage.getItem('xftt-admin-name') || '')
  const isAuthenticated = ref<boolean>(!!localStorage.getItem('xftt-admin-token'))

  function applyLogin(token: string, userName: string): void {
    localStorage.setItem('xftt-admin-token', token)
    localStorage.setItem('xftt-admin-name', userName)
    name.value = userName
    isAuthenticated.value = true
  }

  function logout(): void {
    localStorage.removeItem('xftt-admin-token')
    localStorage.removeItem('xftt-admin-name')
    name.value = ''
    isAuthenticated.value = false
  }

  return { name, isAuthenticated, applyLogin, logout }
})
