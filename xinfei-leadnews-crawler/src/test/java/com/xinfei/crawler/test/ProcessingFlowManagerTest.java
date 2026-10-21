package com.xinfei.crawler.test;

import com.xinfei.crawler.manager.ProcessingFlowManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ProcessingFlowManagerTest {

    @Autowired
    private ProcessingFlowManager processingFlowManager;

    @Test
    public void test() {
        assertNotNull(processingFlowManager);
    }
}
