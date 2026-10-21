package com.xinfei.media.service.impl;

import com.xinfei.common.media.constans.WmMediaConstans;
import com.xinfei.media.service.StatisticsService;
import com.xinfei.model.common.dtos.ResponseResult;
import com.xinfei.model.common.enums.AppHttpCodeEnum;
import com.xinfei.model.mappers.wemedia.WmNewsStatisticsMapper;
import com.xinfei.model.mappers.wemedia.WmUserMapper;
import com.xinfei.model.media.dtos.StatisticDto;
import com.xinfei.model.media.pojos.WmNewsStatistics;
import com.xinfei.model.media.pojos.WmUser;
import com.xinfei.utils.common.BurstUtils;
import com.xinfei.utils.threadlocal.WmThreadLocalUtils;
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
