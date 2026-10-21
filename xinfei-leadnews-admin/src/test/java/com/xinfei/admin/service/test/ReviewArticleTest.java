package com.xinfei.admin.service.test;

import com.xinfei.admin.service.ReviewCrawlerArticleService;
import com.xinfei.admin.service.ReviewMediaArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReviewArticleTest {

    @Autowired
    private ReviewMediaArticleService reviewMediaArticleService;

    @Test
    public void testReview(){
        reviewMediaArticleService.autoReviewArticleByMedia(4101);
    }

    @Autowired
    private ReviewCrawlerArticleService reviewCrawlerArticleService;

    @Test
    public void testReviewCraeler() throws Exception {
        reviewCrawlerArticleService.autoReivewArticleByCrawler(35181);
    }
}
