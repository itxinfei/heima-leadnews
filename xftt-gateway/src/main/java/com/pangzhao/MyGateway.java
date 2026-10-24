package com.pangzhao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 *  服务网关
 */
@SpringBootApplication
@EnableDiscoveryClient
public class MyGateway {
    public static void main(String[] args) {
        SpringApplication.run(MyGateway.class, args);
    }
}
