package com.xinfei.migration.service;

import com.xinfei.model.article.pojos.ApArticleContent;

import java.util.List;

public interface ApArticleContentService {

    public List<ApArticleContent> queryByArticleIds(List<String> ids);

    public ApArticleContent getByArticleId(Integer id);
}
