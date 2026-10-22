package com.xftt.article.service;

import com.xftt.model.article.dtos.ArticleHomeDto;
import com.xftt.model.common.dtos.ResponseResult;
import com.xftt.model.mess.app.ArticleVisitStreamDto;

public interface AppArticleService {
    /**
     * type  1 加载更多  2 加载更新
     * @param dto
     * @param type
     * @return
     */
    ResponseResult load(ArticleHomeDto dto, Short type);

    /**
     * 更新 点赞 阅读数
     * @param dto
     * @return
     */
    ResponseResult updateArticleView(ArticleVisitStreamDto dto);

    /**
     * 加载文章列表数据
     * @param type
     * @param dto
     * @param firstPage
     * @return
     */
    ResponseResult loadV2(Short type,ArticleHomeDto dto,boolean firstPage);

}
