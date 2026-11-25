package com.xftt.login.service;

import com.xftt.model.common.dtos.ResponseResult;
import com.xftt.model.user.dtos.CollectionDto;

/**
 * 用户中心（BE-F03）：关注/粉丝/收藏分页；收藏上报（APP-F10）与统计（APP-F09）
 */
public interface AppUserCenterService {

    ResponseResult follows(Integer page, Integer size);

    ResponseResult fans(Integer page, Integer size);

    ResponseResult collections(Integer page, Integer size);

    /** 收藏 / 取消收藏（APP-F10） */
    ResponseResult collection(CollectionDto dto);

    /** 用户中心统计：关注数 / 粉丝数 / 收藏数（APP-F09） */
    ResponseResult userCenter();
}
