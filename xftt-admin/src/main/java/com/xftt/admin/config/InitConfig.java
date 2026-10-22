package com.xftt.admin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan({"com.xftt.common.mysql.core","com.xftt.common.common.init","com.xftt.common.quartz"})
@MapperScan("com.xftt.admin.dao")
@EnableScheduling
public class InitConfig {
}
