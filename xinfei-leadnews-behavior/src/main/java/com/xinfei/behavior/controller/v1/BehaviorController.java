package com.xinfei.behavior.controller.v1;

import com.xinfei.behavior.apis.BehaviorControllerApi;
import com.xinfei.behavior.service.AppLikesBehaviorService;
import com.xinfei.behavior.service.AppReadBehaviorService;
import com.xinfei.behavior.service.AppShowBehaviorService;
import com.xinfei.behavior.service.AppUnLikesBehaviorService;
import com.xinfei.model.behavior.dtos.LikesBehaviorDto;
import com.xinfei.model.behavior.dtos.ReadBehaviorDto;
import com.xinfei.model.behavior.dtos.ShowBehaviorDto;
import com.xinfei.model.behavior.dtos.UnLikesBehaviorDto;
import com.xinfei.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/behavior")
public class BehaviorController implements BehaviorControllerApi {


    @Autowired
    private AppShowBehaviorService appShowBehaviorService;

    @Override
    @PostMapping("/show_behavior")
    public ResponseResult saveShowBehavior(@RequestBody ShowBehaviorDto dto) {
        return appShowBehaviorService.saveShowBehavior(dto);
    }

    @Autowired
    private AppLikesBehaviorService appLikesBehaviorService;

    @Override
    @PostMapping("/like_behavior")
    public ResponseResult saveLikesBehavior(@RequestBody LikesBehaviorDto dto) {
        return appLikesBehaviorService.saveLikesBehavior(dto);
    }

    @Autowired
    private AppUnLikesBehaviorService appUnLikesBehaviorService;

    @Override
    @PostMapping("/unlike_behavior")
    public ResponseResult saveUnlikesBehavior(@RequestBody UnLikesBehaviorDto dto) {
        return appUnLikesBehaviorService.saveUnLikesBehavior(dto);
    }

    @Autowired
    private AppReadBehaviorService appReadBehaviorService;

    @Override
    @PostMapping("/read_behavior")
    public ResponseResult saveReadBehavior(@RequestBody ReadBehaviorDto dto) {
        return appReadBehaviorService.saveReadBehavior(dto);
    }
}
