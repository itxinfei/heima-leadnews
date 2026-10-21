package com.xinfei.model.mappers.app;

import com.xinfei.model.article.pojos.ApArticleConfig;

import java.util.List;

public interface ApArticleConfigMapper {
    ApArticleConfig selectByArticleId(Integer articleId);

    int insert(ApArticleConfig apArticleConfig);

    List<ApArticleConfig> selectByArticleIds(List<String> articleIds);
}