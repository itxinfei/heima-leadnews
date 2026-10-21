package com.xinfei.images.config;

import org.springframework.boot.web.server.servlet.context.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ServletComponentScan("com.xinfei.common.web.app.security")
public class SecurityConfig {

}