package com.xftt.admin.config;

import org.springframework.boot.web.server.servlet.context.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ServletComponentScan("com.xftt.common.web.admin.security")
public class SecurityConfig {
}
