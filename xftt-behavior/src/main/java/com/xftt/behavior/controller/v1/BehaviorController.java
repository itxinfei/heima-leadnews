package com.xftt.behavior.controller.v1;

import com.xftt.behavior.apis.BehaviorControllerApi;
import com.xftt.behavior.service.AppLikesBehaviorService;
import com.xftt.behavior.service.AppReadBehaviorService;
import com.xftt.behavior.service.AppShowBehaviorService;
import com.xftt.behavior.service.AppUnLikesBehaviorService;
import com.xftt.model.behavior.dtos.LikesBehaviorDto;
import com.xftt.model.behavior.dtos.ReadBehaviorDto;
import com.xftt.model.behavior.dtos.ShowBehaviorDto;
import com.xftt.model.behavior.dtos.UnLikesBehaviorDto;
import com.xftt.model.common.dtos.ResponseResult;
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
