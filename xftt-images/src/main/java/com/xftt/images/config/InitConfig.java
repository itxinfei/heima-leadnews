package com.xftt.images.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.xftt.common.common.init"})
public class InitConfig implements InitializingBean {

    /** 图片对外访问前缀，取自 minio.public-url（与 FILE_SERVER_URL 一致），替代旧 FastDFS 硬编码 IP */
    @Value("${minio.public-url:http://192.168.20.130:9005/xftt/}")
    private String publicUrl;

    public static String PREFIX;

    @Override
    public void afterPropertiesSet() {
        PREFIX = publicUrl;
    }
}
