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
        { path: 'material', component: () => import('../views/material/index.vue') },
      ],
    },
  ],
})

export default router
