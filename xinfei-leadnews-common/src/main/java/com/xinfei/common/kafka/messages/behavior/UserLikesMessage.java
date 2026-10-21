package com.xinfei.common.kafka.messages.behavior;

import com.xinfei.common.kafka.KafkaMessage;
import com.xinfei.model.behavior.pojos.ApLikesBehavior;

public class UserLikesMessage extends KafkaMessage<ApLikesBehavior> {

    public UserLikesMessage(){}

    public UserLikesMessage(ApLikesBehavior data){
        super(data);
    }

    @Override
    public String getType() {
        return "user-likes";
    }
}
