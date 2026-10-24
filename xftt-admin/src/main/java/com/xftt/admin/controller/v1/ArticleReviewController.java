package com.xftt.admin.controller.v1;

import com.xftt.admin.service.ArticleReviewService;
import com.xftt.model.common.dtos.ResponseResult;
import com.xftt.model.media.dtos.WmNewsReviewDto;
import com.xftt.model.media.dtos.WmNewsReviewPageReqDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理端人工审核工作台（AD-F07/F08）。
 * 鉴权沿用 /api/v1/admin 既有 AdminTokenFilter。
 */
@RestController
@RequestMapping("/api/v1/admin/review")
public class ArticleReviewController {

    @Autowired
    private ArticleReviewService articleReviewService;

    /** 待审核/已通过/已驳回列表 */
    @PostMapping("/list")
    public ResponseResult list(@RequestBody WmNewsReviewPageReqDto dto) {
        return articleReviewService.list(dto);
    }

    /** 审核详情 */
    @PostMapping("/detail")
    public ResponseResult detail(@RequestBody WmNewsReviewDto dto) {
        return articleReviewService.detail(dto.getId());
    }

    /** 审核通过 */
    @PostMapping("/pass")
    public ResponseResult pass(@RequestBody WmNewsReviewDto dto) {
        return articleReviewService.pass(dto.getId());
    }

    /** 审核驳回（需带理由） */
    @PostMapping("/reject")
    public ResponseResult reject(@RequestBody WmNewsReviewDto dto) {
        return articleReviewService.reject(dto);
    }
}
