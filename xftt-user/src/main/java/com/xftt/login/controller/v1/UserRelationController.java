package com.xftt.login.controller.v1;

import com.xftt.model.common.dtos.ResponseResult;
import com.xftt.model.user.dtos.UserRelationDto;
import com.xftt.user.apis.UserRelationControllerApi;
import com.xftt.login.service.AppUserCenterService;
import com.xftt.login.service.AppUserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserRelationController implements UserRelationControllerApi {

    @Autowired
    private AppUserRelationService appUserRelationService;

    @Autowired
    private AppUserCenterService appUserCenterService;

    @Override
    @PostMapping("/user_follow")
    public ResponseResult follow(@RequestBody UserRelationDto dto) {
        return appUserRelationService.follow(dto);
    }

    /** 关注列表分页（BE-F03，APP-F09） */
    @GetMapping("/follows")
    public ResponseResult follows(@RequestParam(required = false) Integer page,
                                  @RequestParam(required = false) Integer size) {
        return appUserCenterService.follows(page, size);
    }

    /** 粉丝列表分页（BE-F03，APP-F09） */
    @GetMapping("/fans")
    public ResponseResult fans(@RequestParam(required = false) Integer page,
                               @RequestParam(required = false) Integer size) {
        return appUserCenterService.fans(page, size);
    }

    /** 收藏文章分页（BE-F03，APP-F10） */
    @GetMapping("/collections")
    public ResponseResult collections(@RequestParam(required = false) Integer page,
                                      @RequestParam(required = false) Integer size) {
        return appUserCenterService.collections(page, size);
    }
}
