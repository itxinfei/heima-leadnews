package com.xinfei.media.apis;

import com.xinfei.model.common.dtos.ResponseResult;
import com.xinfei.model.media.pojos.WmUser;

public interface LoginControllerApi {

    public ResponseResult login(WmUser user);
}
