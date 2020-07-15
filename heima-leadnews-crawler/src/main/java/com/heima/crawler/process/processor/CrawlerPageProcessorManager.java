package com.heima.crawler.process.processor;

import com.heima.crawler.helper.CrawlerHelper;
import com.heima.crawler.process.ProcessFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;

/**
 * 爬虫文档流程管理
 * <p>
 * 文档处理管理器将三种文档处理器结合起来进行有序的处理，从初始化URL解析到帮助页以及最终URL的解析
 */
@Component
public class CrawlerPageProcessorManager {

    @Autowired
    private CrawlerHelper crawlerHelper;

    @Resource
    private List<AbstractCrawlerPageProcessor> abstractCrawlerPageProcessorList;

    /**
     * 初始化注入接口顺序的方法
     */
    @PostConstruct
    public void initProcessingFlow() {
        if (null != abstractCrawlerPageProcessorList && !abstractCrawlerPageProcessorList.isEmpty()) {
            abstractCrawlerPageProcessorList.sort(new Comparator<ProcessFlow>() {
                public int compare(ProcessFlow p1, ProcessFlow p2) {
                    if (p1.getPriority() > p2.getPriority()) {
                        return 1;
                    } else if (p1.getPriority() < p2.getPriority()) {
                        return -1;
                    }
                    return 0;
                }
            });
        }
    }

    /**
     * 处理数据
     *
     * @param page
     */
    public void handel(Page page) {
        String handelType = crawlerHelper.getHandelType(page.getRequest());
        String documentType = crawlerHelper.getDocumentType(page.getRequest());
        for (AbstractCrawlerPageProcessor abstractCrawlerPageProcessor : abstractCrawlerPageProcessorList) {
            boolean needHandelType = abstractCrawlerPageProcessor.isNeedHandelType(handelType);
            boolean needDocumentType = abstractCrawlerPageProcessor.isNeedDocumentType(documentType);
            if (needHandelType && needDocumentType) {
                abstractCrawlerPageProcessor.handelPage(page);
            }
        }
    }

}
