import { request } from '../utils/request'
import type { LoginParams, LoginResult, PageResult } from '../types/api'

export function login(params: LoginParams): Promise<LoginResult> {
  return request<LoginResult>('/v1/user/login', { method: 'POST', data: params })
}

/** 用户中心统计（APP-F09）：关注数 / 粉丝数 / 收藏数 */
export interface UserCenterStats {
  follows: number
  fans: number
  collections: number
}

/** 关注记录（我的关注） */
export interface UserFollowItem {
  id: number
  followId: number
  followName: string
  createdTime?: string
}

/** 粉丝记录（我的粉丝） */
export interface UserFanItem {
  id: number
  userId: number
  fansId: number
  fansName: string
  createdTime?: string
}

/** 收藏文章（后端回填标题/封面/作者） */
export interface CollectionItem {
  articleId: number
  title?: string
  images?: string
  authorName?: string
  collectionTime?: string
}

export function getUserCenter(): Promise<UserCenterStats> {
  return request<UserCenterStats>('/v1/user/user_center')
}

export function getFollows(page = 1, size = 10): Promise<PageResult<UserFollowItem>> {
  return request<PageResult<UserFollowItem>>('/v1/user/follows', { data: { page, size } })
}

export function getFans(page = 1, size = 10): Promise<PageResult<UserFanItem>> {
  return request<PageResult<UserFanItem>>('/v1/user/fans', { data: { page, size } })
}

export function getCollections(page = 1, size = 10): Promise<PageResult<CollectionItem>> {
  return request<PageResult<CollectionItem>>('/v1/user/collections', { data: { page, size } })
}

/**
 * 收藏 / 取消收藏（APP-F10）
 * @param articleId 文章ID
 * @param operation 0 收藏，1 取消收藏
 */
export function saveCollection(articleId: number, operation: 0 | 1): Promise<void> {
  return request<void>('/v1/user/collection', {
    method: 'POST',
    data: { articleId, operation },
  })
}

/** 关注 / 取消关注（operation 0 关注，1 取消）；authorId 与 userId 二选一 */
export function followUser(params: {
  authorId?: number
  userId?: number
  articleId?: number
  operation: 0 | 1
}): Promise<void> {
  return request<void>('/v1/user/user_follow', { method: 'POST', data: params })
}
