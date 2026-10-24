package com.xftt.admin.service.impl;

import com.xftt.admin.service.ReviewMediaArticleService;
import com.xftt.model.common.dtos.ResponseResult;
import com.xftt.model.mappers.app.ApUserMessageMapper;
import com.xftt.model.mappers.wemedia.WmNewsMapper;
import com.xftt.model.media.dtos.WmNewsReviewDto;
import com.xftt.model.media.dtos.WmNewsReviewPageReqDto;
import com.xftt.model.media.pojos.WmNews;
import com.xftt.model.user.pojos.ApUserMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * ArticleReviewServiceImpl 单元测试（Mockito，不依赖数据库）。
 * 覆盖：列表、通过（立即发布/定时发布/非待审拒绝）、驳回（置状态+通知/缺理由）。
 */
@ExtendWith(MockitoExtension.class)
class ArticleReviewServiceImplTest {

    @Mock private WmNewsMapper wmNewsMapper;
    @Mock private ReviewMediaArticleService reviewMediaArticleService;
    @Mock private ApUserMessageMapper apUserMessageMapper;

    @InjectMocks private ArticleReviewServiceImpl articleReviewService;

    private WmNews news(Short status, Date publishTime) {
        WmNews n = new WmNews();
        n.setId(100);
        n.setUserId(55L);
        n.setTitle("测试文章");
        n.setStatus(status);
        n.setPublishTime(publishTime);
        return n;
    }

    @Test
    void list_returnsListAndTotal() {
        WmNewsReviewPageReqDto dto = new WmNewsReviewPageReqDto();
        dto.setPage(1);
        dto.setSize(10);
        dto.setStatusList(Arrays.asList((short) 3));
        when(wmNewsMapper.selectReviewList(any())).thenReturn(Arrays.asList(news((short) 3, null)));
        when(wmNewsMapper.countReviewList(any())).thenReturn(1);
        ResponseResult r = articleReviewService.list(dto);
        assertEquals(0, r.getCode().intValue());
        assertNotNull(r.getData());
    }

    @Test
    void pass_publishImmediately_whenNoPublishTime() {
        WmNews n = news((short) 3, null);
        when(wmNewsMapper.selectNewsDetailByPrimaryKey(100)).thenReturn(n);
        ResponseResult r = articleReviewService.pass(100);
        assertEquals(0, r.getCode().intValue());
        verify(reviewMediaArticleService).reviewSuccessSaveAll(n);
        verify(reviewMediaArticleService, never()).updateWmNews(any(), anyShort(), anyString());
    }

    @Test
    void pass_schedule_whenPublishTimeFuture() {
        Date future = new Date(System.currentTimeMillis() + 3600_000L);
        WmNews n = news((short) 3, future);
        when(wmNewsMapper.selectNewsDetailByPrimaryKey(100)).thenReturn(n);
        ResponseResult r = articleReviewService.pass(100);
        assertEquals(0, r.getCode().intValue());
        verify(reviewMediaArticleService).updateWmNews(eq(n), eq((short) 8), anyString());
        verify(reviewMediaArticleService, never()).reviewSuccessSaveAll(any());
    }

    @Test
    void pass_rejected_whenNotInReview() {
        WmNews n = news((short) 9, null);
        when(wmNewsMapper.selectNewsDetailByPrimaryKey(100)).thenReturn(n);
        ResponseResult r = articleReviewService.pass(100);
        assertEquals(501, r.getCode().intValue());
        verifyNoInteractions(reviewMediaArticleService);
    }

    @Test
    void reject_setsFailAndNotifies() {
        WmNews n = news((short) 3, null);
        when(wmNewsMapper.selectNewsDetailByPrimaryKey(100)).thenReturn(n);
        WmNewsReviewDto dto = new WmNewsReviewDto();
        dto.setId(100);
        dto.setReason("内容不合规");
        ResponseResult r = articleReviewService.reject(dto);
        assertEquals(0, r.getCode().intValue());
        verify(reviewMediaArticleService).updateWmNews(eq(n), eq((short) 2), eq("内容不合规"));
        ArgumentCaptor<ApUserMessage> captor = ArgumentCaptor.forClass(ApUserMessage.class);
        verify(apUserMessageMapper).insertSelective(captor.capture());
        assertEquals(109, captor.getValue().getType().intValue());
        assertEquals("文章审核未通过：内容不合规", captor.getValue().getContent());
    }

    @Test
    void reject_requiresReason() {
        WmNewsReviewDto dto = new WmNewsReviewDto();
        dto.setId(100);
        dto.setReason("   ");
        ResponseResult r = articleReviewService.reject(dto);
        assertEquals(501, r.getCode().intValue());
        verifyNoInteractions(reviewMediaArticleService);
        verifyNoInteractions(wmNewsMapper);
    }
}
