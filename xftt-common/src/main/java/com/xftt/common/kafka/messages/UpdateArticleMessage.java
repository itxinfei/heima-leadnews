package com.xftt.common.kafka.messages;

import com.xftt.common.kafka.KafkaMessage;
import com.xftt.model.mess.app.UpdateArticle;

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
