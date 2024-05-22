//package org.harryng.demo.config;
//
//import org.springframework.messaging.simp.config.MessageBrokerRegistry;
//import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
//import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
//
////@Configuration
////@EnableWebSocketMessageBroker
//public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        // Configure the address the client is trying to connect to
//        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
//    }
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        // Set up broadcast node
//        registry.enableSimpleBroker("/topic", "/user");
//        // The / app prefix is required for the client to send messages to the server
//        registry.setApplicationDestinationPrefixes("/app");
//        // Specifies the prefix / user that the user sends (one-to-one)/
//        registry.setUserDestinationPrefix("/user/");
//    }
//}
