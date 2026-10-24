package com.xftt.admin.service.impl;

import com.xftt.admin.service.ReviewMediaArticleService;
import com.xftt.model.mappers.wemedia.WmNewsMapper;
import com.xftt.model.media.pojos.WmNews;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.*;

/**
 * ReviewMediaArticleServiceImpl.publishScheduledArticles 单元测试（Mockito，不依赖数据库）。
 * 覆盖：到点列表逐篇发布、空列表不发布、单篇失败被隔离不影响其余。
 */
@ExtendWith(MockitoExtension.class)
class ReviewMediaArticleServiceImplTest {

    @Mock private WmNewsMapper wmNewsMapper;
    /** 自身代理（@Lazy 注入），逐篇发布走它以保证单篇事务 */
    @Mock private ReviewMediaArticleService self;
    @InjectMocks private ReviewMediaArticleServiceImpl service;

    private WmNews news(Integer id) {
        WmNews n = new WmNews();
        n.setId(id);
        return n;
    }

    @Test
    void publishScheduled_publishesEach() {
        WmNews n1 = news(100);
        WmNews n2 = news(101);
        when(wmNewsMapper.selectScheduledPublishList(0, 100)).thenReturn(Arrays.asList(n1, n2));
        service.publishScheduledArticles();
        verify(self).reviewSuccessSaveAll(n1);
        verify(self).reviewSuccessSaveAll(n2);
    }

    @Test
    void publishScheduled_empty_noPublish() {
        when(wmNewsMapper.selectScheduledPublishList(0, 100)).thenReturn(Collections.emptyList());
        service.publishScheduledArticles();
        verifyNoInteractions(self);
    }

    @Test
    void publishScheduled_singleFailureIsolated() {
        WmNews n1 = news(100);
        WmNews n2 = news(101);
        when(wmNewsMapper.selectScheduledPublishList(0, 100)).thenReturn(Arrays.asList(n1, n2));
        doThrow(new RuntimeException("boom")).when(self).reviewSuccessSaveAll(n1);
        service.publishScheduledArticles();
        // n1 失败被吞，n2 仍应发布
        verify(self).reviewSuccessSaveAll(n2);
    }
}
