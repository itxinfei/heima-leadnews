import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    { path: '/login', component: () => import('../views/login/index.vue') },
    {
      path: '/',
      component: () => import('../views/layout/index.vue'),
      redirect: '/news',
      children: [
        { path: 'news', component: () => import('../views/news/index.vue') },
        { path: 'publish', component: () => import('../views/publish/index.vue') },
        { path: 'material', component: () => import('../views/material/index.vue') },
      ],
    },
  ],
})

// 路由守卫（WM-F11）：除 /login 外的页面需要登录态，未登录重定向并携带回跳地址
router.beforeEach((to) => {
  const token = localStorage.getItem('xftt-wm-token')
  if (to.path !== '/login' && !token) {
    return { path: '/login', query: { redirect: to.fullPath } }
  }
  if (to.path === '/login' && token) {
    return { path: '/' }
  }
})

export default router
