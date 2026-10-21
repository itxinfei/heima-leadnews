package com.xinfei.admin.apis;

import com.xinfei.model.admin.pojos.AdUser;
import com.xinfei.model.common.dtos.ResponseResult;

public interface LoginControllerApi {

    /**
     * 登录
     * @param user
     * @return
     */
    public ResponseResult login(AdUser user);
}
