package com.xftt.login.service;

import com.xftt.model.common.dtos.ResponseResult;
import com.xftt.model.user.dtos.UserRelationDto;

public interface AppUserRelationService {

    /**
     * 用户的关注或取消关注
     * @param dto
     * @return
     */
    public ResponseResult follow(UserRelationDto dto);
}
