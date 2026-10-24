package com.xftt.admin.service;

public interface ReviewMediaArticleService {

    /**
     * 自媒体端发布文章自动审核
     *
     * @param newsId
     */
    void autoReviewArticleByMedia(Integer newsId);

    /**
     * 审核通过并发布：写 ap_article/content/config + ES 索引 + Kafka + 通知，wm_news 置 9。
     * 自动链路（status 4/8）与人工审核通过均复用。
     */
    void reviewSuccessSaveAll(com.xftt.model.media.pojos.WmNews wmNews);

    /**
     * 回写 wm_news 状态与理由。
     */
    void updateWmNews(com.xftt.model.media.pojos.WmNews wmNews, short status, String message);

    /**
     * 定时自动发布：将 status=8（审核通过待发布）且发布时间已到的文章逐篇发布。
     */
    void publishScheduledArticles();
}
