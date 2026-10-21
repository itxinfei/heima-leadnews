package com.xinfei.behavior.service;

import com.xinfei.model.behavior.dtos.UnLikesBehaviorDto;
import com.xinfei.model.common.dtos.ResponseResult;

public interface AppUnLikesBehaviorService {

    /**
     * 保存不喜欢数据
     * @param dto
     * @return
     */
    public ResponseResult saveUnLikesBehavior(UnLikesBehaviorDto dto);
}
