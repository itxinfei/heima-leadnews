package com.xftt.admin.service.impl;

import com.xftt.admin.service.ArticleReviewService;
import com.xftt.admin.service.ReviewMediaArticleService;
import com.xftt.common.media.constans.WmMediaConstans;
import com.xftt.model.common.dtos.ResponseResult;
import com.xftt.model.common.enums.AppHttpCodeEnum;
import com.xftt.model.mappers.app.ApUserMessageMapper;
import com.xftt.model.mappers.wemedia.WmNewsMapper;
import com.xftt.model.media.dtos.WmNewsReviewDto;
import com.xftt.model.media.dtos.WmNewsReviewPageReqDto;
import com.xftt.model.media.pojos.WmNews;
import com.xftt.model.user.pojos.ApUserMessage;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理端人工审核工作台实现（AD-F07/F08）。
 * 通过复用 ReviewMediaArticleService 的发布落库逻辑；驳回回写状态并通知用户。
 */
@Service
@Log4j2
public class ArticleReviewServiceImpl implements ArticleReviewService {

    /** 文章审核驳回的用户消息类型 */
    private static final Integer MSG_TYPE_REVIEW_REJECT = 109;

    @Autowired
    private WmNewsMapper wmNewsMapper;

    @Autowired
    private ReviewMediaArticleService reviewMediaArticleService;

    @Autowired
    private ApUserMessageMapper apUserMessageMapper;

    @Override
    public ResponseResult list(WmNewsReviewPageReqDto dto) {
        if (dto == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        dto.checkParam();
        List<WmNews> list = wmNewsMapper.selectReviewList(dto);
        int total = wmNewsMapper.countReviewList(dto);
        Map<String, Object> map = new HashMap<>();
        map.put("list", list);
        map.put("total", total);
        return ResponseResult.okResult(map);
    }

    @Override
    public ResponseResult detail(Integer id) {
        if (id == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        WmNews wmNews = wmNewsMapper.selectNewsDetailByPrimaryKey(id);
        if (wmNews == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        return ResponseResult.okResult(wmNews);
    }

    @Override
    @Transactional
    public ResponseResult pass(Integer id) {
        if (id == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        WmNews wmNews = wmNewsMapper.selectNewsDetailByPrimaryKey(id);
        // 幂等：仅“需人工审核(3)”可执行通过
        if (wmNews == null || !WmMediaConstans.WM_NEWS_MANUAL_REVIEW_STATUS.equals(wmNews.getStatus())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "文章不在待审核状态，无法通过");
        }
        // 定时发布：发布时间在未来则置“审核通过(8)”待发布，否则立即发布
        if (wmNews.getPublishTime() != null && wmNews.getPublishTime().after(new Date())) {
            reviewMediaArticleService.updateWmNews(wmNews, WmMediaConstans.WM_NEWS_AUTHED_STATUS, "审核通过，待定时发布");
        } else {
            reviewMediaArticleService.reviewSuccessSaveAll(wmNews);
        }
        return ResponseResult.okResult(0);
    }

    @Override
    @Transactional
    public ResponseResult reject(WmNewsReviewDto dto) {
        if (dto == null || dto.getId() == null || !StringUtils.isNotBlank(dto.getReason())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "驳回必须填写理由");
        }
        WmNews wmNews = wmNewsMapper.selectNewsDetailByPrimaryKey(dto.getId());
        // 幂等：仅“需人工审核(3)”可执行驳回
        if (wmNews == null || !WmMediaConstans.WM_NEWS_MANUAL_REVIEW_STATUS.equals(wmNews.getStatus())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "文章不在待审核状态，无法驳回");
        }
        // 回写状态为“审核失败(2)”并记录理由
        reviewMediaArticleService.updateWmNews(wmNews, WmMediaConstans.WM_NEWS_FAIL_STATUS, dto.getReason());
        // 通知用户审核未通过
        if (wmNews.getUserId() != null) {
            ApUserMessage message = new ApUserMessage();
            message.setUserId(wmNews.getUserId());
            message.setContent("文章审核未通过：" + dto.getReason());
            message.setType(MSG_TYPE_REVIEW_REJECT);
            message.setIsRead(false);
            message.setCreatedTime(new Date());
            apUserMessageMapper.insertSelective(message);
        }
        return ResponseResult.okResult(0);
    }
}
