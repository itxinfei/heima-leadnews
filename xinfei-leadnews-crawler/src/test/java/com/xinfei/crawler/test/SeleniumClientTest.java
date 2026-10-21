package com.xinfei.crawler.test;

import com.xinfei.crawler.config.CrawlerConfig;
import com.xinfei.crawler.utils.SeleniumClient;
import com.xinfei.model.crawler.core.cookie.CrawlerHtml;
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