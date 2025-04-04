//package org.harryng.demo.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.annotation.Resource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.List;
//
////@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Resource
//    private ObjectMapper objectMapper;
//
////    @Autowired
////    public WebConfig(ObjectMapper objectMapper) {
////        this.objectMapper = objectMapper;
////    }
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.add(new MappingJackson2HttpMessageConverter(objectMapper));
//    }
//}
