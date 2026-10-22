package com.xftt.behavior.service;

import com.xftt.model.behavior.dtos.ReadBehaviorDto;
import com.xftt.model.common.dtos.ResponseResult;

public interface AppReadBehaviorService {

    /**
     * 存储阅读数据
     * @param dto
     * @return
     */
    public ResponseResult saveReadBehavior(ReadBehaviorDto dto);
}
