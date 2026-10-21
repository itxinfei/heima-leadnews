package com.xinfei.article.controller.v2;

import com.xinfei.article.apis.ArticleHomeControllerApi;
import com.xinfei.article.service.AppArticleService;
import com.xinfei.common.article.constans.ArticleConstans;
import com.xinfei.model.article.dtos.ArticleHomeDto;
import com.xinfei.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/article")
public class ArticleHomeV2Controller implements ArticleHomeControllerApi {

    @Autowired
    private AppArticleService appArticleService;

    @Override
    @PostMapping("/load")
    public ResponseResult load(@RequestBody ArticleHomeDto dto) {
        return appArticleService.loadV2(ArticleConstans.LOADTYPE_LOAD_MORE,dto,true);
    }

    @Override
    @PostMapping("/loadmore")
    public ResponseResult loadMore(@RequestBody ArticleHomeDto dto) {
        return appArticleService.loadV2(ArticleConstans.LOADTYPE_LOAD_MORE,dto,false);
    }

    @Override
    @PostMapping("/loadnew")
    public ResponseResult loadNew(@RequestBody ArticleHomeDto dto) {
        return appArticleService.loadV2(ArticleConstans.LOADTYPE_LOAD_NEW,dto,false);
    }
}
