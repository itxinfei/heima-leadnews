package com.xftt.login.service;

import com.xftt.model.common.dtos.ResponseResult;
import com.xftt.model.user.pojos.ApUser;

public interface ApUserLoginService {

    /**
     * 根据用户名和密码登录验证
     */
    ResponseResult loginAuth(ApUser user);

    /**
     * 根据用户名和密码登录验证  V2
     */
    ResponseResult loginAuthV2(ApUser user);
}
