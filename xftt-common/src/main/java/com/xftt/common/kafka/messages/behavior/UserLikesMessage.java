package com.xftt.common.kafka.messages.behavior;

import com.xftt.common.kafka.KafkaMessage;
import com.xftt.model.behavior.pojos.ApLikesBehavior;

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
