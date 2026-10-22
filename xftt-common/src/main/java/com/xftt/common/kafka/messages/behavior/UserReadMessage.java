package com.xftt.common.kafka.messages.behavior;

import com.xftt.common.kafka.KafkaMessage;
import com.xftt.model.behavior.pojos.ApReadBehavior;

public class UserReadMessage extends KafkaMessage<ApReadBehavior> {

    public UserReadMessage(){}

    public UserReadMessage(ApReadBehavior data){
        super(data);
    }

    @Override
    public String getType() {
        return "user-read";
    }
}
