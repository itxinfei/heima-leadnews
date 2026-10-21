package com.xinfei.admin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan({"com.xinfei.common.mysql.core","com.xinfei.common.common.init","com.xinfei.common.quartz"})
@MapperScan("com.xinfei.admin.dao")
@EnableScheduling
public class InitConfig {
}
