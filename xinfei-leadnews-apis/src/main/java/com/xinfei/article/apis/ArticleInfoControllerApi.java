package com.xinfei.article.apis;

import com.xinfei.model.article.dtos.ArticleInfoDto;
import com.xinfei.model.common.dtos.ResponseResult;

public interface ArticleInfoControllerApi {

    public ResponseResult loadArticleInfo(ArticleInfoDto dto);

    ResponseResult loadArticleBehavior(ArticleInfoDto dto);
}
