package com.xftt.common.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest5_client.Rest5ClientTransport;
import co.elastic.clients.transport.rest5_client.low_level.Rest5Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

/**
 * 心飞头条 - Elasticsearch 9 客户端配置
 */
@Configuration
@PropertySource("classpath:elasticsearch.properties")
public class ElasticsearchConfig {

    @Value("${spring.elasticsearch.uris:http://172.168.20.220:9200}")
    private String uris;

    @Bean(destroyMethod = "close")
    public Rest5Client rest5Client() {
        List<URI> uriList = Arrays.stream(uris.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(URI::create)
                .toList();
        return Rest5Client.builder(uriList).build();
    }

    @Bean
    public ElasticsearchClient elasticsearchClient(Rest5Client rest5Client) {
        Rest5ClientTransport transport = new Rest5ClientTransport(rest5Client, new JacksonJsonpMapper());
        return new ElasticsearchClient(transport);
    }
}
