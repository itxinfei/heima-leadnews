package com.xinfei.common.kafka.messages;

import com.xinfei.common.kafka.KafkaMessage;
import com.xinfei.model.mess.app.UpdateArticle;

public class UpdateArticleMessage extends KafkaMessage<UpdateArticle> {

    public UpdateArticleMessage(){}

    public UpdateArticleMessage(UpdateArticle data){
        super(data);
    }

    @Override
    public String getType() {
        return "update-article";
    }
}
