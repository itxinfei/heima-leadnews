package com.xftt.login.config;

import org.springframework.boot.web.server.servlet.context.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ServletComponentScan("com.xftt.common.web.app.security")
public class SecurityConfig {
}
