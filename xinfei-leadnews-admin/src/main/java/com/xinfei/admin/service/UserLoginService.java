package com.xinfei.admin.service;

import com.xinfei.model.admin.pojos.AdUser;
import com.xinfei.model.common.dtos.ResponseResult;

public interface UserLoginService {

    /**
     * 登录
     *
     * @param user
     * @return
     */
    ResponseResult login(AdUser user);


}
