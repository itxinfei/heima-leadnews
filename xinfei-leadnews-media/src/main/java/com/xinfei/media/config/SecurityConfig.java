package com.xinfei.media.config;

import org.springframework.boot.web.server.servlet.context.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ServletComponentScan("com.xinfei.common.web.wm.security")
public class SecurityConfig {
}
