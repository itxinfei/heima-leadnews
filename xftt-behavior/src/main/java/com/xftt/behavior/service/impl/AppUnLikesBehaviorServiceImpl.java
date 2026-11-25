package com.xftt.behavior.service.impl;

import com.xftt.behavior.service.AppUnLikesBehaviorService;
import com.xftt.behavior.service.BehaviorEntryService;
import com.xftt.common.zookeeper.sequence.Sequences;
import com.xftt.model.behavior.dtos.UnLikesBehaviorDto;
import com.xftt.model.behavior.pojos.ApBehaviorEntry;
import com.xftt.model.behavior.pojos.ApUnlikesBehavior;
import com.xftt.model.common.dtos.ResponseResult;
import com.xftt.model.common.enums.AppHttpCodeEnum;
import com.xftt.model.mappers.app.ApBehaviorEntryMapper;
import com.xftt.model.mappers.app.ApUnlikesBehaviorMapper;
import com.xftt.model.user.pojos.ApUser;
import com.xftt.utils.threadlocal.AppThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@SuppressWarnings("all")
public class AppUnLikesBehaviorServiceImpl implements AppUnLikesBehaviorService {

    @Autowired
    private ApBehaviorEntryMapper apBehaviorEntryMapper;

    @Autowired
    private ApUnlikesBehaviorMapper apUnlikesBehaviorMapper;

    @Autowired
    private BehaviorEntryService behaviorEntryService;

    @Autowired
    private Sequences sequences;

    @Override
    public ResponseResult saveUnLikesBehavior(UnLikesBehaviorDto dto) {
        //获取用户信息，获取设备id
        ApUser user = AppThreadLocalUtils.getUser();
        //根据当前的用户信息或设备id查询行为实体 ap_behavior_entry
        if(user==null&&dto.getEquipmentId()==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        Long userId = null;
        if(user!=null){
            userId=user.getId();
        }
        //行为实体不存在时自动创建，避免新用户首次行为上报报“无效参数”
        ApBehaviorEntry apBehaviorEntry = behaviorEntryService.ensureEntry(userId, dto.getEquipmentId());

        ApUnlikesBehavior alb = new ApUnlikesBehavior();
        alb.setId(sequences.sequenceApUnlikes());
        alb.setEntryId(apBehaviorEntry.getId());
        alb.setCreatedTime(new Date());
        alb.setArticleId(dto.getArticleId());
        alb.setType(dto.getType());
        int insert = apUnlikesBehaviorMapper.insert(alb);

        return ResponseResult.okResult(insert);
    }
}
