package com.xinfei.model.mappers.wemedia;

import com.xinfei.model.media.dtos.StatisticDto;
import com.xinfei.model.media.pojos.WmNewsStatistics;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WmNewsStatisticsMapper {
	List<WmNewsStatistics> findByTimeAndUserId(@Param("burst") String burst,@Param("userId") Long userId,
											   @Param("dto") StatisticDto dto);
}