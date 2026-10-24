package com.xftt.crawler.job;

import com.xftt.crawler.manager.ProcessingFlowManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.quartz.JobExecutionContext;

import java.lang.reflect.Field;

import static org.mockito.Mockito.verify;

/**
 * CrawlerForwardQuartz 防回归单测：确保正向抓取入口 handel() 已恢复调用（不再被误注释）。
 */
@ExtendWith(MockitoExtension.class)
class CrawlerForwardQuartzTest {

    @Mock
    private ProcessingFlowManager processingFlowManager;

    private CrawlerForwardQuartz job;

    @BeforeEach
    void setUp() throws Exception {
        job = new CrawlerForwardQuartz();
        Field f = CrawlerForwardQuartz.class.getDeclaredField("processingFlowManager");
        f.setAccessible(true);
        f.set(job, processingFlowManager);
    }

    @Test
    void execute_callsHandel() throws Exception {
        job.executeInternal((JobExecutionContext) null);
        verify(processingFlowManager).handel();
    }
}
