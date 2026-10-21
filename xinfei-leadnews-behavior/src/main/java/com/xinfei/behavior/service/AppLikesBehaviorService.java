package com.xinfei.behavior.service;

import com.xinfei.model.behavior.dtos.LikesBehaviorDto;
import com.xinfei.model.common.dtos.ResponseResult;

public interface AppLikesBehaviorService {

    public ResponseResult saveLikesBehavior(LikesBehaviorDto dto);
}
