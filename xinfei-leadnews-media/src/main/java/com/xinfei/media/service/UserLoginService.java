package com.xinfei.media.service;

import com.xinfei.model.common.dtos.ResponseResult;
import com.xinfei.model.media.pojos.WmUser;

public interface UserLoginService {

    /**
     * 登录方法
     */
    public ResponseResult login(WmUser user);
}
