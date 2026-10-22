package com.xftt.article.test;

import com.xftt.article.ArticleJarApplication;
import com.xftt.article.service.AppArticleService;
import com.xftt.common.article.constans.ArticleConstans;
import com.xftt.model.article.dtos.ArticleHomeDto;
import com.xftt.model.common.dtos.ResponseResult;
import com.xftt.model.user.pojos.ApUser;
import com.xftt.utils.threadlocal.AppThreadLocalUtils;
import org.apache.ibatis.io.ResolverUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ArticleJarApplication.class)
public class ArticleTest {

    @Autowired
    private AppArticleService appArticleService;

    @Test
    public void testArticle(){
        ApUser apUser = new ApUser();
        apUser.setId(2104l);
        AppThreadLocalUtils.setUser(apUser);
        ArticleHomeDto dto = new ArticleHomeDto();
        dto.setTag("3");
        ResponseResult result = appArticleService.loadV2(ArticleConstans.LOADTYPE_LOAD_MORE, null,true);
        System.out.println(result.getData());
    }
}
