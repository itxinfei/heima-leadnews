package com.xftt.crawler.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan({"com.xftt.common.common.init", "com.xftt.common.mysql.core",
        "com.xftt.common.kafka","com.xftt.common.quartz"})
@EnableScheduling
public class InitConfig {
}
