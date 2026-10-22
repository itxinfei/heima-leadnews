package com.xftt.media.service;

import com.xftt.model.common.dtos.ResponseResult;
import com.xftt.model.media.dtos.StatisticDto;

public interface StatisticsService {
	/**  
	* 查找图文统计数据  
	* @param dto  
	* @return  
	*/  
	ResponseResult findWmNewsStatistics(StatisticDto dto);
}