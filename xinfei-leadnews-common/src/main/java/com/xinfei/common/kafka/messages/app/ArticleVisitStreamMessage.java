package com.xinfei.common.kafka.messages.app;

import com.xinfei.common.kafka.KafkaMessage;
import com.xinfei.model.mess.app.ArticleVisitStreamDto;

public class ArticleVisitStreamMessage extends KafkaMessage<ArticleVisitStreamDto> {

    public ArticleVisitStreamMessage(){}

    public ArticleVisitStreamMessage(ArticleVisitStreamDto data){
        super(data);
    }

    @Override
    public String getType() {
        return "article-visit-stream";
    }
}
