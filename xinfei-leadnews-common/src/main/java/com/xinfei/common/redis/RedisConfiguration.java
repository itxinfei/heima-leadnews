package com.xinfei.common.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:redis.properties")
@ConfigurationProperties(prefix = "spring.data.redis")
public class RedisConfiguration {
}
