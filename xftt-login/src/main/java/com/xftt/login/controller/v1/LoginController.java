package com.xftt.login.controller.v1;

import com.xftt.login.apis.LoginControllerApi;
import com.xftt.login.service.ApUserLoginService;
import com.xftt.model.common.dtos.ResponseResult;
import com.xftt.model.user.pojos.ApUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController implements LoginControllerApi {

    @Autowired
    private ApUserLoginService apUserLoginService;

    @Override
    @PostMapping("/login_auth")
    public ResponseResult login(@RequestBody ApUser user) {
        return apUserLoginService.loginAuth(user);
    }
}
