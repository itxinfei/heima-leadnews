package com.xftt.images.service;

import com.xftt.common.kafka.messages.app.ApHotArticleMessage;

public interface HotArticleImageService {

    /**
     * 处理热点文章图片
     * @param message
     */
    public void handleHotImage(ApHotArticleMessage message);
}
