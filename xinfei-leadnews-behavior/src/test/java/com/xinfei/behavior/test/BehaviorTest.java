package com.xinfei.behavior.test;

import com.xinfei.behavior.BehaviorJarApplication;
import com.xinfei.behavior.service.AppLikesBehaviorService;
import com.xinfei.behavior.service.AppShowBehaviorService;
import com.xinfei.model.article.pojos.ApArticle;
import com.xinfei.model.behavior.dtos.LikesBehaviorDto;
import com.xinfei.model.behavior.dtos.ShowBehaviorDto;
import com.xinfei.model.user.pojos.ApUser;
import com.xinfei.utils.threadlocal.AppThreadLocalUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = BehaviorJarApplication.class)
public class BehaviorTest {

    @Autowired
    private AppShowBehaviorService appShowBehaviorService;

    @Test
    public void testSave(){
        ApUser user = new ApUser();
        user.setId(1l);
        AppThreadLocalUtils.setUser(user);
        ShowBehaviorDto dto = new ShowBehaviorDto();
        List<ApArticle> list = new ArrayList<>();
        ApArticle apArticle = new ApArticle();
        apArticle.setId(10130);
        list.add(apArticle);
        dto.setArticleIds(list);
        appShowBehaviorService.saveShowBehavior(dto);
    }

    @Autowired
    private AppLikesBehaviorService appLikesBehaviorService;

    @Test
    public void testLikesSave(){
        ApUser user = new ApUser();
        user.setId(1l);
        AppThreadLocalUtils.setUser(user);
        LikesBehaviorDto dto = new LikesBehaviorDto();
        dto.setEntryId(10120);
        dto.setOperation((short)0);
        dto.setType((short)0);
        appLikesBehaviorService.saveLikesBehavior(dto);
    }

}
