package com.xinfei.media.service;

import com.xinfei.model.common.dtos.ResponseResult;
import com.xinfei.model.media.dtos.StatisticDto;

public interface StatisticsService {
	/**  
	* 查找图文统计数据  
	* @param dto  
	* @return  
	*/  
	ResponseResult findWmNewsStatistics(StatisticDto dto);
}