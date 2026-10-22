package com.xftt.crawler.service;

import com.xftt.model.crawler.pojos.ClIpPool;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class CrawlerIpPoolServiceTest {

    @Autowired
    private CrawlerIpPoolService crawlerIpPoolService;

    @Test
    public void testSaveCrawlerIpPool(){
        ClIpPool clIpPool = new ClIpPool();
        clIpPool.setIp("2222.3333.444.5555");
        clIpPool.setPort(1111);
        clIpPool.setEnable(true);
        clIpPool.setCreatedTime(new Date());
        crawlerIpPoolService.saveCrawlerIpPool(clIpPool);
    }


    @Test
    public void testCheckExist(){
        boolean b = crawlerIpPoolService.checkExist("2222.3333.444.555555666555", 1111);
        System.out.println(b);
    }
}
