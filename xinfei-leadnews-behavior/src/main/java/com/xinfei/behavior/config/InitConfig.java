package com.xinfei.behavior.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.xinfei.common.common.init","com.xinfei.common.kafka"})
public class InitConfig {
}
