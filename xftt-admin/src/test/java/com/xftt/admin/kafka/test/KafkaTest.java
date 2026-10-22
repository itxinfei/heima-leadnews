package com.xftt.admin.kafka.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootTest
public class KafkaTest {

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Test
    public void test(){
        try {
            kafkaTemplate.send("topic.test","123KEY","itcast...");
            System.out.println("==========消息发送了============");
            Thread.sleep(5000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
