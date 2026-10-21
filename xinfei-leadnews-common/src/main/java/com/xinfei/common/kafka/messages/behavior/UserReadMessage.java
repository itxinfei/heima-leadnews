package com.xinfei.common.kafka.messages.behavior;

import com.xinfei.common.kafka.KafkaMessage;
import com.xinfei.model.behavior.pojos.ApReadBehavior;

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
