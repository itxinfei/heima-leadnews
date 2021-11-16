package com.heima.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 用于实现APP端文章的获取与搜索等功能；还包括频道、标签等功能
 */
@SpringBootApplication
public class ArticleJarApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArticleJarApplication.class,args);
    }
}
