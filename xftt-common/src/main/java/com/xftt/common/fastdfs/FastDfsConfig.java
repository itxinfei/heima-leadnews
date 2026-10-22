package com.xftt.common.fastdfs;

import io.minio.MinioClient;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 心飞头条 - MinIO 对象存储配置（替换原 FastDFS）
 */
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "minio")
@PropertySource("classpath:minio.properties")
public class FastDfsConfig {

    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucket;
    private String publicUrl;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }
}
