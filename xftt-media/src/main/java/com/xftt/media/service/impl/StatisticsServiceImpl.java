package com.xftt.media.service.impl;

import com.xftt.common.media.constans.WmMediaConstans;
import com.xftt.media.service.StatisticsService;
import com.xftt.model.common.dtos.ResponseResult;
import com.xftt.model.common.enums.AppHttpCodeEnum;
import com.xftt.model.mappers.wemedia.WmNewsStatisticsMapper;
import com.xftt.model.mappers.wemedia.WmUserMapper;
import com.xftt.model.media.dtos.StatisticDto;
import com.xftt.model.media.pojos.WmNewsStatistics;
import com.xftt.model.media.pojos.WmUser;
import com.xftt.utils.common.BurstUtils;
import com.xftt.utils.threadlocal.WmThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    WmUserMapper wmUserMapper;

    @Autowired
    WmNewsStatisticsMapper wmNewsStatisticsMapper;

    @Override
    public ResponseResult findWmNewsStatistics(StatisticDto dto) {
        if(dto==null && dto.getType()==null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        if(WmMediaConstans.WM_NEWS_STATISTIC_CUR!=dto.getType() && (dto.getStime()==null || dto.getEtime()==null)){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //查询用户
        WmUser user = WmThreadLocalUtils.getUser();
        WmUser wmUser = wmUserMapper.selectById(user.getId());
        String burst = BurstUtils.groudOne(wmUser.getApUserId());
        List<WmNewsStatistics> list = wmNewsStatisticsMapper.findByTimeAndUserId(burst, wmUser.getApUserId(), dto);
        return ResponseResult.okResult(list);
    }
}
