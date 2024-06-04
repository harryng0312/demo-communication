package org.harryng.demo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.harryng.demo.api.util.TextUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestConfig {

    @Bean
    public ObjectMapper getObjectMapper() {
        return TextUtil.getObjectMapper();
    }
}
