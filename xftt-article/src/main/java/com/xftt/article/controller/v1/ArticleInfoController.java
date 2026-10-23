package com.xftt.article.controller.v1;

import com.xftt.article.apis.ArticleInfoControllerApi;
import com.xftt.article.service.AppArticleInfoService;
import com.xftt.model.article.dtos.ArticleInfoDto;
import com.xftt.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleInfoController implements ArticleInfoControllerApi {


    @Autowired
    private AppArticleInfoService appArticleInfoService;

    @Override
    @PostMapping("/load_article_info")
    public ResponseResult loadArticleInfo(@RequestBody ArticleInfoDto dto) {
        return appArticleInfoService.getArticleInfo(dto.getArticleId());
    }

    /**
     * 详情扁平聚合端点：前端 GET /api/v1/article/{id}，返回 ArticleDetail 形状
     */
    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Integer id) {
        return appArticleInfoService.getArticleDetail(id);
    }

    @Override
    @PostMapping("/load_article_behavior")
    public ResponseResult loadArticleBehavior(@RequestBody ArticleInfoDto dto) {
        return appArticleInfoService.loadArticleBehavior(dto);
    }
}
