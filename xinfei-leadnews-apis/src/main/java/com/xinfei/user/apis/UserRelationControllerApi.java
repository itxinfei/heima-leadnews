package com.xinfei.user.apis;

import com.xinfei.model.common.dtos.ResponseResult;
import com.xinfei.model.user.dtos.UserRelationDto;

public interface UserRelationControllerApi {

    /**
     * 关注或取消关注
     */
    ResponseResult follow(UserRelationDto dto);
}
