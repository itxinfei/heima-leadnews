package com.xftt.media.kafka;

import com.xftt.common.kafka.KafkaSender;
import com.xftt.common.kafka.messages.SubmitArticleAuthMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminMessageSender {

    @Autowired
    private KafkaSender kafkaSender;

    public void sendMessage(SubmitArticleAuthMessage message){
        kafkaSender.sendSubmitArticleAuthMessage(message);
    }
}
