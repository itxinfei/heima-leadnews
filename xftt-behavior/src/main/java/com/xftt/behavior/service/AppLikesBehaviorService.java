package com.xftt.behavior.service;

import com.xftt.model.behavior.dtos.LikesBehaviorDto;
import com.xftt.model.common.dtos.ResponseResult;

public interface AppLikesBehaviorService {

    public ResponseResult saveLikesBehavior(LikesBehaviorDto dto);
}
