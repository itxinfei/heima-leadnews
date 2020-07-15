package com.heima.crawler.process.processor.impl;

import com.heima.crawler.process.entity.CrawlerConfigProperty;
import com.heima.crawler.process.processor.AbstractCrawlerPageProcessor;
import com.heima.model.crawler.enums.CrawlerEnum;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;

import java.util.List;

/**
 * 从初始化URL下载的页面中个人空间的URL并加入到下载列表，并设置解析类型为帮助页
 * <p>
 * 抓取初始的页面
 */
@Component
@Log4j2
public class CrawlerInitPageProcessor extends AbstractCrawlerPageProcessor {

    @Autowired
    private CrawlerConfigProperty crawlerConfigProperty;

    /**
     * 处理数据
     *
     * @param page
     */
    @Override
    public void handelPage(Page page) {
        String initXpath = crawlerConfigProperty.getInitCrawlerXpath();
        //抓取帮助页面List
        List<String> helpUrl = page.getHtml().xpath(initXpath).links().all();
        //打印list
        for (String s : helpUrl) {
            System.out.println(s);
        }
        addSpiderRequest(helpUrl, page.getRequest(), CrawlerEnum.DocumentType.HELP);
    }

    /**
     * 需要处理的爬取类型
     * 初始化只处理 正向爬取
     *
     * @param handelType
     * @return
     */
    @Override
    public boolean isNeedHandelType(String handelType) {
        return CrawlerEnum.HandelType.FORWARD.name().equals(handelType);
    }

    /**
     * 需要处理的文档类型
     * 只处理初始化的URK
     *
     * @param documentType
     * @return
     */
    @Override
    public boolean isNeedDocumentType(String documentType) {
        return CrawlerEnum.DocumentType.INIT.name().equals(documentType);
    }

    /**
     * 优先级
     *
     * @return
     */
    @Override
    public int getPriority() {
        return 100;
    }
}
