package org.harryng.demo.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

//@Configuration
public class RestConfig {

    @Bean
    public ObjectMapper getObjectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.findAndRegisterModules()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false)
                .configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        return objectMapper;
    }

//    @Bean
//    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
//        return builder -> {
//            // formatter
//            final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//            // deserializers
//            builder.deserializers(new LocalDateDeserializer(dateFormatter));
//            builder.deserializers(new LocalDateTimeDeserializer(dateTimeFormatter));
//            // serializers
//            builder.serializers(new LocalDateSerializer(dateFormatter));
//            builder.serializers(new LocalDateTimeSerializer(dateTimeFormatter));
//        };
//    }
}
