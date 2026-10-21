package com.xinfei.migration.job;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.xinfei.common.quartz.AbstractJob;
import com.xinfei.migration.service.ArticleQuantityService;
import lombok.extern.log4j.Log4j2;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@DisallowConcurrentExecution

public class MigrationDbToHBaseQuartz extends AbstractJob {
    @Override
    public String[] triggerCron() {
        return new String[]{"0 0/2 * * * ?"};
    }

    @Autowired
    private ArticleQuantityService articleQuantityService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("开始同步文章数据到hbase");
        articleQuantityService.dbToHbase();
        log.info("同步文章数据到hbase完成");
    }
}
