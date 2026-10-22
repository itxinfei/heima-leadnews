package com.xftt.media.apis;

import com.xftt.model.common.dtos.ResponseResult;
import com.xftt.model.media.pojos.WmUser;

public interface LoginControllerApi {

    public ResponseResult login(WmUser user);
}
