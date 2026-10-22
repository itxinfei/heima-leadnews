package com.xftt.migration.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan({"com.xftt.common.common.init","com.xftt.common.mongo","com.xftt.common.mysql.core", "com.xftt.common.quartz","com.xftt.common.hbase","com.xftt.common.kafka"})
@EnableScheduling
public class InitConfig {
}