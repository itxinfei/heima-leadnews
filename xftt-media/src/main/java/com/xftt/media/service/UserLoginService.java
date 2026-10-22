package com.xftt.media.service;

import com.xftt.model.common.dtos.ResponseResult;
import com.xftt.model.media.pojos.WmUser;

public interface UserLoginService {

    /**
     * 登录方法
     */
    public ResponseResult login(WmUser user);
}
