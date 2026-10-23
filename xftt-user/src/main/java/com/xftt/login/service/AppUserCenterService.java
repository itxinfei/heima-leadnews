package com.xftt.login.service;

import com.xftt.model.common.dtos.ResponseResult;

/**
 * 用户中心（BE-F03）：关注/粉丝/收藏分页
 */
public interface AppUserCenterService {

    ResponseResult follows(Integer page, Integer size);

    ResponseResult fans(Integer page, Integer size);

    ResponseResult collections(Integer page, Integer size);
}
