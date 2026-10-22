package com.xftt.admin.apis;

import com.xftt.model.admin.pojos.AdUser;
import com.xftt.model.common.dtos.ResponseResult;

public interface LoginControllerApi {

    /**
     * 登录
     * @param user
     * @return
     */
    public ResponseResult login(AdUser user);
}
