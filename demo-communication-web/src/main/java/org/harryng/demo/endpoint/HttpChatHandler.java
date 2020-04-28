package org.harryng.demo.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.LocalDateTime;

public class HttpChatHandler extends TextWebSocketHandler {
    static Logger logger = LoggerFactory.getLogger(HttpChatHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Object token = session.getAttributes().get("token");
        if (token != null) {
            // The user is connected successfully and put into the online user cache
            WsSessionManager.add(token.toString(), session);
        } else {
            throw new RuntimeException("User login has expired!");
        }
    }

    /**
     * Receive message event
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Get messages from clients
        String payload = message.getPayload();
        Object token = session.getAttributes().get("token");
        logger.info("server Receive " + token + " Transmitted " + payload);
        session.sendMessage(new TextMessage("server Send to " + token + " news " + payload + " " + LocalDateTime.now().toString()));
    }

    /**
     * socket When disconnected
     *
     * @param session
     * @param status
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Object token = session.getAttributes().get("token");
        if (token != null) {
            // User exit, remove cache
            WsSessionManager.remove(token.toString());
        }
    }
}
