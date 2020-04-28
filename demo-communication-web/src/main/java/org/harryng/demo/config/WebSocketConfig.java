package org.harryng.demo.config;

import org.harryng.demo.endpoint.HttpChatHandler;
import org.harryng.demo.endpoint.HttpChatInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

//@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private HttpChatHandler httpAuthHandler;
    @Autowired
    private HttpChatInterceptor chatInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(httpAuthHandler, "/ws/chat-handler/*")
                .addInterceptors(chatInterceptor)
                .setAllowedOrigins("*");
    }
}
