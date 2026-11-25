package com.xftt.behavior.config;

import org.springframework.boot.web.server.servlet.context.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 启用 APP 端 token 过滤器，使行为上报接口可获取登录用户上下文。
 */
@Configuration
@ServletComponentScan("com.xftt.common.web.app.security")
public class SecurityConfig {
}
