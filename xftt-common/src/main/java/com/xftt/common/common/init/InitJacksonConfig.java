package com.xftt.common.common.init;

import com.xftt.common.common.contants.Contants;
import com.xftt.common.jackson.ConfusionModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.json.JsonMapper;

@Configuration
public class InitJacksonConfig {

    @Bean
    public JsonMapper jsonMapper() {
        JsonMapper jsonMapper = ConfusionModule.registerModule(JsonMapper.builder());
        Contants.objectMapper = jsonMapper;
        return jsonMapper;
    }

}
