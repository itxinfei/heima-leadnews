package com.xftt.images.kafka.listener;

import tools.jackson.databind.ObjectMapper;
import com.xftt.common.kafka.KafkaListener;
import com.xftt.common.kafka.KafkaTopicConfig;
import com.xftt.common.kafka.messages.app.ApHotArticleMessage;
import com.xftt.images.service.HotArticleImageService;

import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class HotArticleListener implements KafkaListener<String,String> {

    @Autowired
    private KafkaTopicConfig kafkaTopicConfig;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private HotArticleImageService hotArticleImageService;

    @Override
    public String topic() {
        return kafkaTopicConfig.getHotArticle();
    }

    @Override
    public void onMessage(ConsumerRecord<String, String> data, Consumer<?, ?> consumer) {
        log.info("接收到热文章消息为:{}", data);
        String value = data.value();
        ApHotArticleMessage message = mapper.readValue(value, ApHotArticleMessage.class);
        hotArticleImageService.handleHotImage(message);
    }
}
