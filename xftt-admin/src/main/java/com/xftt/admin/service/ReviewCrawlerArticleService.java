package com.xftt.admin.service;

import com.xftt.model.crawler.pojos.ClNews;

public interface ReviewCrawlerArticleService {

    /**
     * 爬虫端发布文章审核
     *
     * @throws Exception
     */
    void autoReivewArticleByCrawler(ClNews clNews) throws Exception;

    void autoReivewArticleByCrawler() throws Exception;

    void autoReivewArticleByCrawler(Integer clNewsId) throws Exception;
}
