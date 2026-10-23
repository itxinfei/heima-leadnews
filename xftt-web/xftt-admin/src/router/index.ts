import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/login', component: () => import('../views/login/index.vue') },
    {
      path: '/',
      component: () => import('../views/layout/index.vue'),
      redirect: '/users',
      children: [
        { path: 'users', component: () => import('../views/users/index.vue') },
        { path: 'channels', component: () => import('../views/channels/index.vue') },
        { path: 'sensitive', component: () => import('../views/sensitive/index.vue') },
      ],
    },
  ],
})

// 路由守卫（AD-F11）：除 /login 外的页面需要登录态，未登录重定向并携带回跳地址
router.beforeEach((to) => {
  const token = localStorage.getItem('xftt-admin-token')
  if (to.path !== '/login' && !token) {
    return { path: '/login', query: { redirect: to.fullPath } }
  }
  if (to.path === '/login' && token) {
    return { path: '/' }
  }
})

export default router
