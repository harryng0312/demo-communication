package org.harryng.demo.config;

import jakarta.annotation.Resource;
import org.harryng.demo.endpoint.handler.TextChatHandler;
import org.harryng.demo.endpoint.interceptor.ConversationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Resource
    private TextChatHandler httpAuthHandler;
    @Resource
    private ConversationInterceptor chatInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(httpAuthHandler, "/ws/chat-handler")
                .addInterceptors(chatInterceptor)
                .setAllowedOrigins("*");
//        org.springframework.beans.factory.
    }
}
