package com.xftt.user.apis;

import com.xftt.model.common.dtos.ResponseResult;
import com.xftt.model.user.dtos.UserRelationDto;

public interface UserRelationControllerApi {

    /**
     * 关注或取消关注
     */
    ResponseResult follow(UserRelationDto dto);
}
