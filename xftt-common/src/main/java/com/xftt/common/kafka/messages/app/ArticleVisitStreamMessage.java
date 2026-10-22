package com.xftt.common.kafka.messages.app;

import com.xftt.common.kafka.KafkaMessage;
import com.xftt.model.mess.app.ArticleVisitStreamDto;

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
