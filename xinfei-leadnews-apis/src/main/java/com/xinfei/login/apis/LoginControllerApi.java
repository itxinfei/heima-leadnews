package com.xinfei.login.apis;

import com.xinfei.model.common.dtos.ResponseResult;
import com.xinfei.model.user.pojos.ApUser;

public interface LoginControllerApi {

    ResponseResult login(ApUser user);
}
