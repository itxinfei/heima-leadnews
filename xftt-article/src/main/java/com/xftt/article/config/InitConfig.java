package com.xftt.article.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan({"com.xftt.common.mysql.core","com.xftt.common.common.init","com.xftt.common.quartz","com.xftt.common.kafka","com.xftt.common.kafkastream","com.xftt.common.elasticsearch","com.xftt.common.mongo"})
@EnableScheduling
public class InitConfig {
}
