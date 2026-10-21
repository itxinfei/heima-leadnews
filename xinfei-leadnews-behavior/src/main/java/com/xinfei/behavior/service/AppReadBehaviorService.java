package com.xinfei.behavior.service;

import com.xinfei.model.behavior.dtos.ReadBehaviorDto;
import com.xinfei.model.common.dtos.ResponseResult;

public interface AppReadBehaviorService {

    /**
     * 存储阅读数据
     * @param dto
     * @return
     */
    public ResponseResult saveReadBehavior(ReadBehaviorDto dto);
}
