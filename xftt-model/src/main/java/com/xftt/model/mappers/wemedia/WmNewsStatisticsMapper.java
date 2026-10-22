package com.xftt.model.mappers.wemedia;

import com.xftt.model.media.dtos.StatisticDto;
import com.xftt.model.media.pojos.WmNewsStatistics;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WmNewsStatisticsMapper {
	List<WmNewsStatistics> findByTimeAndUserId(@Param("burst") String burst,@Param("userId") Long userId,
											   @Param("dto") StatisticDto dto);
}