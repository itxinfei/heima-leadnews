package com.xftt.article.service.impl;

import com.xftt.common.common.pojo.EsIndexEntity;
import com.xftt.model.article.pojos.ApArticle;
import com.xftt.model.article.pojos.ApArticleConfig;
import com.xftt.model.article.pojos.ApArticleContent;
import com.xftt.model.crawler.core.parse.ZipUtils;
import com.xftt.model.mappers.app.ApArticleConfigMapper;
import com.xftt.model.mappers.app.ApArticleContentMapper;
import com.xftt.model.mappers.app.ApArticleMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

/**
 * ApArticleSearchServiceImpl.buildBackfillEntities 单元测试（反射调用私有方法）。
 * 覆盖：字段映射 + content gunzip、过滤已删除/下架、空 content 仍保留。
 */
@ExtendWith(MockitoExtension.class)
class ApArticleSearchServiceImplTest {

    @Mock private ApArticleMapper apArticleMapper;
    @Mock private ApArticleContentMapper apArticleContentMapper;
    @Mock private ApArticleConfigMapper apArticleConfigMapper;
    @InjectMocks private ApArticleSearchServiceImpl service;

    private ApArticle article(Integer id, String title, Integer channelId, Long authorId) {
        ApArticle a = new ApArticle();
        a.setId(id);
        a.setTitle(title);
        a.setChannelId(channelId);
        a.setAuthorId(authorId);
        a.setPublishTime(new Date());
        return a;
    }

    @SuppressWarnings("unchecked")
    private List<EsIndexEntity> build(List<ApArticle> batch) throws Exception {
        Method m = ApArticleSearchServiceImpl.class.getDeclaredMethod("buildBackfillEntities", List.class);
        m.setAccessible(true);
        return (List<EsIndexEntity>) m.invoke(service, batch);
    }

    @Test
    void build_mapsFieldsAndGunzipsContent() throws Exception {
        ApArticle a = article(1, "标题A", 10, 100L);
        ApArticleContent c = new ApArticleContent();
        c.setArticleId(1);
        c.setContent(ZipUtils.gzip("正文内容"));
        when(apArticleContentMapper.selectByArticleIds(anyList())).thenReturn(Arrays.asList(c));
        when(apArticleConfigMapper.selectByArticleIds(anyList())).thenReturn(Collections.emptyList());

        List<EsIndexEntity> entities = build(Arrays.asList(a));
        assertEquals(1, entities.size());
        EsIndexEntity e = entities.get(0);
        assertEquals(1L, e.getId().longValue());
        assertEquals("标题A", e.getTitle());
        assertEquals("正文内容", e.getContent());
        assertEquals(10L, e.getChannelId().longValue());
        assertEquals(100L, e.getUserId().longValue());
        assertEquals(1L, e.getStatus().longValue());
    }

    @Test
    void build_filtersDeletedAndDown() throws Exception {
        ApArticle a1 = article(1, "正常", 10, 1L);
        ApArticle a2 = article(2, "已删", 10, 2L);
        ApArticle a3 = article(3, "下架", 10, 3L);
        when(apArticleContentMapper.selectByArticleIds(anyList())).thenReturn(Collections.emptyList());
        ApArticleConfig c2 = new ApArticleConfig();
        c2.setArticleId(2);
        c2.setIsDelete(true);
        ApArticleConfig c3 = new ApArticleConfig();
        c3.setArticleId(3);
        c3.setIsDown(true);
        when(apArticleConfigMapper.selectByArticleIds(anyList())).thenReturn(Arrays.asList(c2, c3));

        List<EsIndexEntity> entities = build(Arrays.asList(a1, a2, a3));
        assertEquals(1, entities.size());
        assertEquals(1L, entities.get(0).getId().longValue());
    }

    @Test
    void build_nullContent_stillIncluded() throws Exception {
        ApArticle a = article(5, "无正文", 10, 1L);
        when(apArticleContentMapper.selectByArticleIds(anyList())).thenReturn(Collections.emptyList());
        when(apArticleConfigMapper.selectByArticleIds(anyList())).thenReturn(Collections.emptyList());

        List<EsIndexEntity> entities = build(Arrays.asList(a));
        assertEquals(1, entities.size());
        assertNull(entities.get(0).getContent());
        assertEquals("无正文", entities.get(0).getTitle());
    }
}
