package com.xftt.behavior.service;

import com.xftt.model.behavior.dtos.UnLikesBehaviorDto;
import com.xftt.model.common.dtos.ResponseResult;

public interface AppUnLikesBehaviorService {

    /**
     * 保存不喜欢数据
     * @param dto
     * @return
     */
    public ResponseResult saveUnLikesBehavior(UnLikesBehaviorDto dto);
}
