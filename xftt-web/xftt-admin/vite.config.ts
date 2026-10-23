import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// 平台后台直连后端服务；频道管理走 media 的 /api/v1/channel/channels（返回完整 AdChannel 列表）
// 注意：必须用更具体的 /api/v1/channel/channels 命中 media(:9006)，避免与 article 的 /api/v1/channel/list 混淆
export default defineConfig({
  plugins: [vue()],
  server: {
    port: 8082,
    proxy: {
      '/api/v1/channel/channels': { target: 'http://localhost:9006', changeOrigin: true },
      '/api/v1/admin': { target: 'http://localhost:9007', changeOrigin: true },
      '/login': { target: 'http://localhost:9007', changeOrigin: true },
    },
  },
})
