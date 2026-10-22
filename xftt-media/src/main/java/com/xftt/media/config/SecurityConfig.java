package com.xftt.media.config;

import org.springframework.boot.web.server.servlet.context.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ServletComponentScan("com.xftt.common.web.wm.security")
public class SecurityConfig {
}
