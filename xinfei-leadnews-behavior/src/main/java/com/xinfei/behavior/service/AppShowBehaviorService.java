package com.xinfei.behavior.service;

import com.xinfei.model.behavior.dtos.ShowBehaviorDto;
import com.xinfei.model.common.dtos.ResponseResult;

public interface AppShowBehaviorService {
    ResponseResult saveShowBehavior(ShowBehaviorDto dto);
}
