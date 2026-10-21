package com.xinfei.common.kafka.messages;

import com.xinfei.common.kafka.KafkaMessage;
import com.xinfei.model.mess.admin.SubmitArticleAuto;

public class SubmitArticleAuthMessage extends KafkaMessage<SubmitArticleAuto> {

    public SubmitArticleAuthMessage(){}
    public SubmitArticleAuthMessage(SubmitArticleAuto data){
        super(data);
    }

    @Override
    public String getType() {
        return "submit-article-auth";
    }
}
