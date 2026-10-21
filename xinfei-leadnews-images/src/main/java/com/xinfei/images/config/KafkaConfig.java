package com.xinfei.images.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.xinfei.common.kafka","com.xinfei.common.kafkastream"})
public class KafkaConfig {
}
