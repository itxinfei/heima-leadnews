package com.xftt.admin.job;

import com.xftt.admin.service.ReviewMediaArticleService;
import com.xftt.common.quartz.AbstractJob;
import lombok.extern.log4j.Log4j2;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 定时自动发布：将 status=8（审核通过待发布）且发布时间已到的文章自动发布。
 * 由 QuartzScanJob 启动自动注册，cron 每分钟执行。
 */
@Component
@DisallowConcurrentExecution
@Log4j2
public class WmNewsAutoPublishJob extends AbstractJob {

    @Override
    public String[] triggerCron() {
        return new String[]{"0 0/1 * * * ?"};
    }

    @Autowired
    private ReviewMediaArticleService reviewMediaArticleService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        long start = System.currentTimeMillis();
        log.info("开始定时发布任务");
        try {
            reviewMediaArticleService.publishScheduledArticles();
        } catch (Exception e) {
            log.error("定时发布任务异常", e);
        }
        log.info("定时发布任务结束，耗时:{}ms", System.currentTimeMillis() - start);
    }

    @Override
    public String descTrigger() {
        return "每分钟执行，发布到期的 status=8 文章";
    }
}
