package com.xftt.article.controller.v2;

import com.xftt.article.apis.ArticleSearchControllerApi;
import com.xftt.article.service.ApArticleSearchService;
import com.xftt.model.article.dtos.UserSearchDto;
import com.xftt.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/article/search")
public class ArticleSearchV2Controller implements ArticleSearchControllerApi {

    @Autowired
    private ApArticleSearchService apArticleSearchService;

    @Override
    @PostMapping("/load_search_history")
    public ResponseResult findUserSearch(@RequestBody UserSearchDto dto) {
        return apArticleSearchService.findUserSearch(dto);
    }

    @Override
    @PostMapping("/del_search")
    public ResponseResult delUserSearch(@RequestBody UserSearchDto dto) {
        return apArticleSearchService.delUserSearch(dto);
    }

    @Override
    @PostMapping("/clear_search")
    public ResponseResult clearUserSearch(@RequestBody UserSearchDto dto) {
        return apArticleSearchService.clearUserSearch(dto);
    }

    @Override
    @PostMapping("/load_hot_keywords")
    public ResponseResult hotkeywords(@RequestBody UserSearchDto dto) {
        return apArticleSearchService.hotkeywords(dto.getHotDate());
    }

    @Override
    @PostMapping("/associate_search")
    public ResponseResult searchassociate(@RequestBody UserSearchDto dto) {
        return apArticleSearchService.searchAssocicationV2(dto);
    }

    @Override
    @PostMapping("/article_search")
    public ResponseResult esArticleSearch(@RequestBody UserSearchDto dto) {
        return apArticleSearchService.esArticleSearch(dto);
    }
}
