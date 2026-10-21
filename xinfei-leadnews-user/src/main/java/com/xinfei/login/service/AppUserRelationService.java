package com.xinfei.login.service;

import com.xinfei.model.common.dtos.ResponseResult;
import com.xinfei.model.user.dtos.UserRelationDto;

public interface AppUserRelationService {

    /**
     * 用户的关注或取消关注
     * @param dto
     * @return
     */
    public ResponseResult follow(UserRelationDto dto);
}
