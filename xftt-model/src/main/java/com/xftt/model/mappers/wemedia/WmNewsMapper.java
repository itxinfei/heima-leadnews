package com.xftt.model.mappers.wemedia;

import com.xftt.model.media.dtos.WmNewsPageReqDto;
import com.xftt.model.media.dtos.WmNewsReviewPageReqDto;
import com.xftt.model.media.pojos.WmNews;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface WmNewsMapper {
    
	/**
     * 根据主键修改
     * @param record
     * @return
     */
    int updateByPrimaryKey(WmNews record);
    /**
     * 添加草稿新闻
     * @param dto
     * @return
     */
    int insertNewsForEdit(WmNews dto);

    /**
     * 查询根据dto条件
     * @param dto
     * @param uid
     * @return
     */
    List<WmNews> selectBySelective(@Param("dto") WmNewsPageReqDto dto,@Param("uid") Long uid);

    /**
     * 查询总数统计
     * @param dto
     * @param uid
     * @return
     */
    int countSelectBySelective(@Param("dto") WmNewsPageReqDto dto,@Param("uid") Long uid);
    WmNews selectNewsDetailByPrimaryKey(Integer id);
    int deleteByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WmNews record);

    /**
     * 管理端审核工作台：按 status 集合分页查询（不按 user_id）
     */
    List<WmNews> selectReviewList(@Param("dto") WmNewsReviewPageReqDto dto);

    /**
     * 管理端审核工作台：按 status 集合计数
     */
    int countReviewList(@Param("dto") WmNewsReviewPageReqDto dto);
}