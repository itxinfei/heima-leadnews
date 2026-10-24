package com.xftt.model.media.dtos;

import com.xftt.model.common.dtos.PageRequestDto;
import lombok.Data;

import java.util.List;

/**
 * 管理端人工审核工作台列表查询 DTO。
 * statusList：待审核=[3]、已通过=[8,9]、已驳回=[2]。
 */
@Data
public class WmNewsReviewPageReqDto extends PageRequestDto {

    /** 文章状态集合（对应工作台 Tab） */
    private List<Short> statusList;
    /** 标题关键字（模糊查询） */
    private String keyWord;
}
