package com.xftt.media.controller.v1;

import com.xftt.media.apis.LoginControllerApi;
import com.xftt.media.service.UserLoginService;
import com.xftt.model.common.dtos.ResponseResult;
import com.xftt.model.media.pojos.WmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController implements LoginControllerApi {

    @Autowired
    private UserLoginService userLoginService;

    @Override
    @RequestMapping("/in")
    public ResponseResult login(@RequestBody WmUser user) {
        return userLoginService.login(user);
    }
}
