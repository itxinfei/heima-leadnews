package com.xftt.crawler.service;

import com.xftt.model.crawler.pojos.ClNewsAdditional;
import org.apache.commons.lang3.StringUtils;
import org.apache.hbase.thirdparty.io.netty.handler.codec.sctp.SctpOutboundByteStreamHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CrawlerNewsAdditionalServiceTest {

    @Autowired
    private CrawlerNewsAdditionalService crawlerNewsAdditionalService;

    @Test
    public void testQueryList(){
        ClNewsAdditional clNewsAdditional = new ClNewsAdditional();
        clNewsAdditional.setUrl("https://blog.csdn.net/weixin_43976602/article/details/96971651");
        List<ClNewsAdditional> clNewsAdditionals = crawlerNewsAdditionalService.queryList(clNewsAdditional);
        System.out.println(clNewsAdditionals);
    }

    @Test
    public void testCheckExist(){
        boolean b = crawlerNewsAdditionalService.checkExist("https://blog.csdn.net/weixin_43976602/article/details/96971651");
        System.out.println(b);
    }
}
