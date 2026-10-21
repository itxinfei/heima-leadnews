package com.xinfei.common.quartz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 心飞头条 - QuartzScanJob 已 stub 化（阶段 1），保留 QuartzJobBean 继承以兼容 QuartzConfig。
 * 阶段 2 将按 Quartz 最新 API 重写。
 */
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class QuartzScanJob extends QuartzJobBean {

    private static final Logger log = LogManager.getLogger(QuartzScanJob.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("QuartzScanJob.executeInternal - stub");
    }
}