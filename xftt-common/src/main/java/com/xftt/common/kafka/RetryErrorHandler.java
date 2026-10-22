package com.xftt.common.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.CommonLoggingErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

@Component
public class RetryErrorHandler extends CommonLoggingErrorHandler {
    private static final Logger logger = LoggerFactory.getLogger(RetryErrorHandler.class);
    private static final int RETRY_COUNT = 10;
    private static final int TIME_OUT = 3_600_000;

    @Autowired
    private KafkaSender sender;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public boolean handleOne(Exception thrownException, ConsumerRecord<?, ?> record,
                             Consumer<?, ?> consumer, MessageListenerContainer container) {
        super.handleOne(thrownException, record, consumer, container);
        if (record != null) {
            try {
                KafkaMessage<?> message = mapper.readValue((String) record.value(), KafkaMessage.class);
                message.addRetry();
                long time = System.currentTimeMillis() - message.getTime();
                if (message.getRetry() > RETRY_COUNT || time > TIME_OUT) {
                    logger.info("超时或者尝试{}次后，抛弃消息[topic:{}][{}]", RETRY_COUNT, record.topic(), record.value());
                } else {
                    sender.sendMesssage(record.topic(), (String) record.key(), message);
                    logger.info("处理失败重新回滚到队列[retry:{}][topic:{}][key:{}]",
                            message.getRetry(), record.topic(), record.key());
                }
            } catch (Exception e) {
                sender.sendMesssageNoWrap(record.topic(), (String) record.key(), (String) record.value());
            }
        }
        return true;
    }
}
