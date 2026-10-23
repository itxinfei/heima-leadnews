import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// 自媒体端接口全部归属 xftt-media(:9006)；按路径前缀分发，dev 直连后端服务（统一网关路由配置好后可改回 :10000）
export default defineConfig({
  plugins: [vue()],
  server: {
    port: 8081,
    proxy: {
      '/api/v1/media': { target: 'http://localhost:9006', changeOrigin: true },
      '/api/v1/channel': { target: 'http://localhost:9006', changeOrigin: true },
      '/api/v1/statistics': { target: 'http://localhost:9006', changeOrigin: true },
      '/login': { target: 'http://localhost:9006', changeOrigin: true },
    },
  },
})
