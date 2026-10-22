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

export default router
