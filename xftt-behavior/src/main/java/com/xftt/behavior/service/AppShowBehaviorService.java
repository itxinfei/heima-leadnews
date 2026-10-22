package com.xftt.behavior.service;

import com.xftt.model.behavior.dtos.ShowBehaviorDto;
import com.xftt.model.common.dtos.ResponseResult;

public interface AppShowBehaviorService {
    ResponseResult saveShowBehavior(ShowBehaviorDto dto);
}
