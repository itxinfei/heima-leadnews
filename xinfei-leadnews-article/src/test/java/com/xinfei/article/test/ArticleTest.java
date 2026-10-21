package com.xinfei.article.test;

import com.xinfei.article.ArticleJarApplication;
import com.xinfei.article.service.AppArticleService;
import com.xinfei.common.article.constans.ArticleConstans;
import com.xinfei.model.article.dtos.ArticleHomeDto;
import com.xinfei.model.common.dtos.ResponseResult;
import com.xinfei.model.user.pojos.ApUser;
import com.xinfei.utils.threadlocal.AppThreadLocalUtils;
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
