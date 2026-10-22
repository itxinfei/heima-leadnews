package com.xftt.login.service;

import com.xftt.model.behavior.dtos.FollowBehaviorDto;
import com.xftt.model.common.dtos.ResponseResult;

public interface AppFollowBehaviorService {
    /**
     * 存储关注行为数据
     */
    public ResponseResult saveFollowBehavior(FollowBehaviorDto dto);
}
