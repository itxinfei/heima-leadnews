package com.xftt.migration.kafka.listener;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tools.jackson.databind.ObjectMapper;
import com.xftt.common.kafka.KafkaListener;
import com.xftt.common.kafka.KafkaTopicConfig;
import com.xftt.common.kafka.messages.app.ApHotArticleMessage;
import com.xftt.migration.service.ApHotArticleService;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Log4j2
@Component

public class MigrationHotArticleListener implements KafkaListener<String,String> {

    @Autowired
    private KafkaTopicConfig kafkaTopicConfig;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ApHotArticleService apHotArticleService;

    @Override
    public String topic() {
        return kafkaTopicConfig.getHotArticle();
    }

    @Override
    public void onMessage(ConsumerRecord<String, String> data, Consumer<?, ?> consumer) {
        log.info("接收到热点文章数据:{}",data);
        String value = data.value();
        if(null != value){
            ApHotArticleMessage message = null;
            try {
                message = mapper.readValue(value, ApHotArticleMessage.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Integer articleId = message.getData().getArticleId();
            if(null != articleId){
                apHotArticleService.hotApArticleSync(articleId);
            }
        }
    }
}
