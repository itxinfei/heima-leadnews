package com.xinfei.login.service;

import com.xinfei.model.behavior.dtos.FollowBehaviorDto;
import com.xinfei.model.common.dtos.ResponseResult;

public interface AppFollowBehaviorService {
    /**
     * 存储关注行为数据
     */
    public ResponseResult saveFollowBehavior(FollowBehaviorDto dto);
}
