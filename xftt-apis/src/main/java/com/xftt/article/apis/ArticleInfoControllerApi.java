package com.xftt.article.apis;

import com.xftt.model.article.dtos.ArticleInfoDto;
import com.xftt.model.common.dtos.ResponseResult;

public interface ArticleInfoControllerApi {

    public ResponseResult loadArticleInfo(ArticleInfoDto dto);

    ResponseResult loadArticleBehavior(ArticleInfoDto dto);
}
