package com.xftt.article.kafka.listener;

import tools.jackson.databind.ObjectMapper;
import com.xftt.article.service.AppArticleService;
import com.xftt.common.kafka.KafkaListener;
import com.xftt.common.kafka.KafkaTopicConfig;
import com.xftt.common.kafka.messages.app.ArticleVisitStreamMessage;
import com.xftt.model.mess.app.ArticleVisitStreamDto;
import com.xftt.utils.common.DataConvertUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Log4j2
public class ArticleIncrHandleListener implements KafkaListener<String,String> {

    @Autowired
    private KafkaTopicConfig kafkaTopicConfig;

    @Autowired
    private AppArticleService appArticleService;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public String topic() {
        return kafkaTopicConfig.getArticleIncrHandle();
    }

    @Override
    public void onMessage(ConsumerRecord<String, String> data, Consumer<?, ?> consumer) {
        log.info("接收到的消息为:{}", data);
        String value = data.value();
        ArticleVisitStreamMessage message = mapper.readValue(value, ArticleVisitStreamMessage.class);
        ArticleVisitStreamDto dto = message.getData();
        appArticleService.updateArticleView(dto);
    }
}
