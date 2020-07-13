package com.pangzhao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 注册中心
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MyEureka {
    public static void main(String[] args) {
        SpringApplication.run(MyEureka.class, args);
    }
}
