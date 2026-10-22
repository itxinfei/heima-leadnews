package com.xftt.common.kafka.messages.app;

import com.xftt.common.kafka.KafkaMessage;
import com.xftt.model.article.pojos.ApHotArticles;

public class ApHotArticleMessage extends KafkaMessage<ApHotArticles> {

    public ApHotArticleMessage(){}

    public ApHotArticleMessage(ApHotArticles data){
        super(data);
    }

    @Override
    public String getType() {
        return "hot-article";
    }
}
