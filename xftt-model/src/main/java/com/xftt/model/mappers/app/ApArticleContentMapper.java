package com.xftt.model.mappers.app;

import com.xftt.model.article.pojos.ApArticleContent;

import java.util.List;

public interface ApArticleContentMapper {
    ApArticleContent selectByArticleId(Integer articleId);
    void insert(ApArticleContent apArticleContent);

    List<ApArticleContent> selectByArticleIds(List<String> articleIds);
}