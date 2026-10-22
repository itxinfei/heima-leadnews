package com.xftt.crawler.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AdLabelServiceTest {

    @Autowired
    private AdLabelService adLabelService;

    @Test
    public void testGetLabelIds(){
        String labelIds = adLabelService.getLabelIds("java,docker,8888xxxx");
        System.out.println(labelIds);
    }

    @Test
    public void testGetAdChannelByLabelIds(){
        Integer adChannelByLabelIds = adLabelService.getAdChannelByLabelIds("1,2");
        System.out.println(adChannelByLabelIds);
    }
}
