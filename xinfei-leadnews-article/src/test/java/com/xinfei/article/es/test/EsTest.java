package com.xinfei.article.es.test;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import com.xinfei.common.common.pojo.EsIndexEntity;
import com.xinfei.model.article.dtos.ArticleHomeDto;
import com.xinfei.model.article.pojos.ApArticle;
import com.xinfei.model.article.pojos.ApArticleContent;
import com.xinfei.model.common.constants.ESIndexConstants;
import com.xinfei.model.mappers.app.ApArticleContentMapper;
import com.xinfei.model.mappers.app.ApArticleMapper;
import com.xinfei.utils.common.ZipUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

@SpringBootTest
public class EsTest {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    @Autowired
    private ApArticleMapper apArticleMapper;

    @Autowired
    private ApArticleContentMapper apArticleContentMapper;

    @Test
    public void testSave() throws IOException {

        ArticleHomeDto dto = new ArticleHomeDto();
        dto.setSize(50);
        dto.setTag("__all__");
        List<ApArticle> apArticles = apArticleMapper.loadArticleListByLocation(dto, null);
        for (ApArticle apArticle : apArticles) {
            ApArticleContent apArticleContent = apArticleContentMapper.selectByArticleId(apArticle.getId());

            EsIndexEntity esIndexEntity = new EsIndexEntity();
            esIndexEntity.setChannelId(new Long(apArticle.getChannelId()));
            esIndexEntity.setId(apArticle.getId().longValue());
            esIndexEntity.setContent(ZipUtils.gunzip(apArticleContent.getContent()));
            esIndexEntity.setPublishTime(apArticle.getPublishTime());
            esIndexEntity.setStatus(new Long(1));
            esIndexEntity.setTag("article");
            esIndexEntity.setTitle(apArticle.getTitle());
            IndexRequest<EsIndexEntity> request = IndexRequest.of(builder -> builder
                    .index(ESIndexConstants.ARTICLE_INDEX)
                    .id(apArticle.getId().toString())
                    .document(esIndexEntity));
            IndexResponse result = elasticsearchClient.index(request);
            if (result == null) {
                throw new RuntimeException("插入更新索引失败!");
            }
        }
        elasticsearchClient.indices().refresh(builder -> builder.index(ESIndexConstants.ARTICLE_INDEX));


    }
}
