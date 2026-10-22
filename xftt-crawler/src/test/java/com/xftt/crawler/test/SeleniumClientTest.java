package com.xftt.crawler.test;

import com.xftt.crawler.config.CrawlerConfig;
import com.xftt.crawler.utils.SeleniumClient;
import com.xftt.model.crawler.core.cookie.CrawlerHtml;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SeleniumClientTest {

    @Autowired
    private SeleniumClient seleniumClient;

    @Test
    public void test(){
        CrawlerHtml crawlerHtml =  seleniumClient.getCrawlerHtml("http://www.baidu.com",null,null);
        System.out.println(crawlerHtml.getHtml());
    }

}