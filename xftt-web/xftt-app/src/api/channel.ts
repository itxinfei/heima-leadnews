import { request } from '../utils/request'

/** 频道选项 */
export interface ChannelOption {
  id: number
  name: string
}

/** 频道列表兜底数据：接口不可用时保持首页可用 */
export const DEFAULT_CHANNELS: ChannelOption[] = [
  { id: 0, name: '推荐' },
  { id: 1, name: '热点' },
  { id: 2, name: '科技' },
  { id: 3, name: '财经' },
  { id: 4, name: '娱乐' },
  { id: 5, name: '体育' },
  { id: 6, name: '汽车' },
  { id: 7, name: '教育' },
]

/** 频道接口可降级：失败时首页使用 DEFAULT_CHANNELS 兜底，不弹错误提示 */
export function getChannelList(): Promise<ChannelOption[]> {
  return request<ChannelOption[]>('/v1/channel/list', { silent: true })
}
