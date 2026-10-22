package com.xftt.admin.controller.v1;


import com.xftt.admin.apis.LoginControllerApi;
import com.xftt.admin.service.UserLoginService;
import com.xftt.model.admin.pojos.AdUser;
import com.xftt.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController implements LoginControllerApi {

    @Autowired
    private UserLoginService userLoginService;

    @Override
    @PostMapping("/in")
    public ResponseResult login(@RequestBody AdUser user) {
        return userLoginService.login(user);
    }
}
