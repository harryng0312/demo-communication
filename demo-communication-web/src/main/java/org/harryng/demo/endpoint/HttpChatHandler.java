package org.harryng.demo.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.websocket.EncodeException;
import java.io.IOException;
import java.time.LocalDateTime;

public class HttpChatHandler extends TextWebSocketHandler {
    static Logger logger = LoggerFactory.getLogger(HttpChatHandler.class);

    private ChatMessageDecoder decoder = new ChatMessageDecoder();
    private ChatMessageEncoder encoder = new ChatMessageEncoder();

    private static void broadcast(WebSocketMessage<?> message) throws IOException, EncodeException {
        WsSessionManager.getSessionPool().entrySet().forEach(entry -> {
            synchronized (entry) {
                try {
                    WebSocketSession session = entry.getValue();
                    session.sendMessage(message);
                    //.getBasicRemote().sendObject(message);
                } catch (IOException e) {
                    logger.error("", e);
                }
            }
        });
    }

    @Override
//    @DestinationVariable
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Object token = session.getAttributes().get("username");
        if (token != null) {
            // The user is connected successfully and put into the online user cache
            WsSessionManager.add(token.toString(), session);

            ChatMessage message = new ChatMessage();
            message.setFrom((String)token);
            message.setContent("Connected!");
            broadcast(new TextMessage(encoder.encode(message)));
        } else {
            throw new RuntimeException("User login has expired!");
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Get messages from clients
        String payload = message.getPayload();
        ChatMessage chatMsg = decoder.decode(payload);
        chatMsg.setFrom((String)session.getAttributes().get("username"));
        Object token = session.getAttributes().get("username");
        logger.info("server Receive " + token + " Transmitted " + payload);
        session.sendMessage(new TextMessage("server Send to " + token + " news " + payload + " " + LocalDateTime.now().toString()));
        broadcast(new TextMessage(encoder.encode(chatMsg)));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        Object token = session.getAttributes().get("username");
        if (token != null) {
            // User exit, remove cache
            WsSessionManager.remove(token.toString());
        }
    }
}
