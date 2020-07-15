package com.heima.crawler.process.original.impl;

import com.heima.crawler.config.CrawlerConfig;
import com.heima.crawler.process.entity.ProcessFlowData;
import com.heima.crawler.process.original.AbstractOriginalDataProcess;
import com.heima.model.crawler.core.parse.ParseItem;
import com.heima.model.crawler.core.parse.impl.CrawlerParseItem;
import com.heima.model.crawler.enums.CrawlerEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 把url列表转换为对象
 */
@Component
public class CsdnOriginalDataProcess extends AbstractOriginalDataProcess {

    @Autowired
    private CrawlerConfig crawlerConfig;

    /**
     * 把url列表转换为对象
     *
     * @param processFlowData
     * @return
     */
    @Override
    public List<ParseItem> parseOriginalRequestData(ProcessFlowData processFlowData) {
        List<ParseItem> parseItemList = null;
        //从crawlerConfigProperty 中获取初始化URL列表
        List<String> initCrawlerUrlList = crawlerConfig.getInitCrawlerUrlList();
        //打印URL列表
        System.out.println("URL:" + initCrawlerUrlList.toString());
        if (initCrawlerUrlList != null && !initCrawlerUrlList.isEmpty()) {
            parseItemList = initCrawlerUrlList.stream().map(url -> {
                CrawlerParseItem parseItem = new CrawlerParseItem();
                url = url + "?rnd=" + System.currentTimeMillis();
                parseItem.setUrl(url);
                parseItem.setDocumentType(CrawlerEnum.DocumentType.INIT.name());
                parseItem.setHandelType(processFlowData.getHandelType().name());
                return parseItem;
            }).collect(Collectors.toList());
        }
        return parseItemList;
    }

    /**
     * 优先级
     *
     * @return
     */
    @Override
    public int getPriority() {
        return 10;
    }
}
