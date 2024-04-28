//package org.harryng.demo.config;
//
//import jakarta.annotation.Resource;
//import org.harryng.demo.endpoint.HttpChatHandler;
//import org.harryng.demo.endpoint.HttpChatInterceptor;
//import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
//import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
////@EnableWebSocket
//public class WebSocketConfig implements WebSocketConfigurer {
//
//    @Resource
//    private HttpChatHandler httpAuthHandler;
//    @Resource
//    private HttpChatInterceptor chatInterceptor;
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
//        registry.addHandler(httpAuthHandler, "/ws/chat-handler/*")
//                .addInterceptors(chatInterceptor)
//                .setAllowedOrigins("*");
//    }
//}
