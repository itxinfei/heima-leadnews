package com.xinfei.migration.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan({"com.xinfei.common.common.init","com.xinfei.common.mongo","com.xinfei.common.mysql.core", "com.xinfei.common.quartz","com.xinfei.common.hbase","com.xinfei.common.kafka"})
@EnableScheduling
public class InitConfig {
}