package org.harryng.demo.endpoint;

import org.harryng.demo.model.ChatMessage;
import org.harryng.demo.model.ChatMessageDecoder;
import org.harryng.demo.model.ChatMessageEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/ws/chat-basic/{username}",
        encoders = {ChatMessageEncoder.class}, decoders = {ChatMessageDecoder.class}
)
public class ChatEndpoint {
    static Logger logger = LoggerFactory.getLogger(ChatEndpoint.class);
    private Session session;
    private static final Set<ChatEndpoint> chatEndpoints = new CopyOnWriteArraySet<>();
    private static final Map<String, String> users = new HashMap<>();

    private static void broadcast(ChatMessage message) throws IOException, EncodeException {
        chatEndpoints.forEach(endpoint -> {
            synchronized (endpoint) {
                try {
                    endpoint.session.getBasicRemote().sendObject(message);
                } catch (IOException | EncodeException e) {
                    logger.error("", e);
                }
            }
        });
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("username") String username) throws IOException, EncodeException {
        logger.info("session id: " + session.getId());
        this.session = session;
        chatEndpoints.add(this);
        users.put(session.getId(), username);
        ChatMessage message = new ChatMessage();
        message.setFrom(username);
        message.setContent("Connected!");
        broadcast(message);
    }

    @OnMessage
    public void onMessage(Session session, ChatMessage message) throws IOException, EncodeException {
        message.setFrom(users.get(session.getId()));
        logger.info("from: " + message.getContent());
        broadcast(message);
    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        chatEndpoints.remove(this);
        ChatMessage message = new ChatMessage();
        message.setFrom(users.get(session.getId()));
        message.setContent("Disconnected!");
        broadcast(message);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        logger.error("", throwable);
    }
}
