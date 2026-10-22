package com.xftt.login.apis;

import com.xftt.model.common.dtos.ResponseResult;
import com.xftt.model.user.pojos.ApUser;

public interface LoginControllerApi {

    ResponseResult login(ApUser user);
}
