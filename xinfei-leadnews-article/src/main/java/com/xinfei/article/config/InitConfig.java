package com.xinfei.article.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan({"com.xinfei.common.mysql.core","com.xinfei.common.common.init","com.xinfei.common.quartz","com.xinfei.common.kafka","com.xinfei.common.kafkastream"})
@EnableScheduling
public class InitConfig {
}
