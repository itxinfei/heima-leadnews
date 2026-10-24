package com.xftt.admin.service;

import com.xftt.model.common.dtos.ResponseResult;
import com.xftt.model.media.dtos.WmNewsReviewDto;
import com.xftt.model.media.dtos.WmNewsReviewPageReqDto;

/**
 * 管理端人工审核工作台服务（AD-F07/F08）。
 */
public interface ArticleReviewService {

    /** 审核工作台列表（待审核[3]/已通过[8,9]/已驳回[2]） */
    ResponseResult list(WmNewsReviewPageReqDto dto);

    /** 审核详情（含正文 content） */
    ResponseResult detail(Integer id);

    /** 审核通过：publishTime 在未来则置 8 待定时发布，否则立即发布 */
    ResponseResult pass(Integer id);

    /** 审核驳回：置 2 + 理由 + 通知用户 */
    ResponseResult reject(WmNewsReviewDto dto);
}
