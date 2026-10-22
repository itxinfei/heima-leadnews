package com.xftt.admin.service;

import com.xftt.model.admin.pojos.AdUser;
import com.xftt.model.common.dtos.ResponseResult;

public interface UserLoginService {

    /**
     * 登录
     *
     * @param user
     * @return
     */
    ResponseResult login(AdUser user);


}
