package com.xinfei.migration.test;

import com.xinfei.model.article.pojos.ApArticle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest
public class MongoTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testSave(){
        ApArticle apArticle = new ApArticle();
        apArticle.setId(22222);
        apArticle.setTitle("黑马头条说明书");
        mongoTemplate.insert(apArticle);
    }

    @Test
    public void testGetOne(){
        ApArticle article = mongoTemplate.findById(22222, ApArticle.class);
        System.out.println(article);
    }
}
