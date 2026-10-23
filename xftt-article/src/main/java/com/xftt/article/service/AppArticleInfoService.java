package com.xftt.article.service;

import com.xftt.model.article.dtos.ArticleInfoDto;
import com.xftt.model.common.dtos.ResponseResult;

public interface AppArticleInfoService {
    /**
     * 加载文章详情
     */
    public ResponseResult getArticleInfo(Integer articleId);
    /**
     * 加载文章详情（扁平聚合：ap_article 字段 + 正文 HTML），供 GET /{id} 端点使用
     */
    ResponseResult getArticleDetail(Integer articleId);
    /**
     * 加载文章详情的初始化配置信息，比如关注，收藏，点赞，不喜欢
     */
    ResponseResult loadArticleBehavior(ArticleInfoDto dto);
}
