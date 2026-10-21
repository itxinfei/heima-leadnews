package com.xinfei.crawler.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan({"com.xinfei.common.common.init", "com.xinfei.common.mysql.core",
        "com.xinfei.common.kafka","com.xinfei.common.quartz"})
@EnableScheduling
public class InitConfig {
}
