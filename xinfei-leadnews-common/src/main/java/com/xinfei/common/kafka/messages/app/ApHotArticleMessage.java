package com.xinfei.common.kafka.messages.app;

import com.xinfei.common.kafka.KafkaMessage;
import com.xinfei.model.article.pojos.ApHotArticles;

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
