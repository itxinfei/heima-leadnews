package com.xftt.behavior.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.xftt.common.common.init","com.xftt.common.kafka"})
public class InitConfig {
}
