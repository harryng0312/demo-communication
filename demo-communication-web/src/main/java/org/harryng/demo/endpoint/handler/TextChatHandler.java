package org.harryng.demo.endpoint.handler;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.api.constant.RequestParams;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.endpoint.WsSessionManager;
import org.harryng.demo.impl.cache.CacheManager;
import org.harryng.demo.impl.util.SessionUtil;
import org.harryng.demo.model.ChatMessage;
import org.harryng.demo.model.ChatMessageDecoder;
import org.harryng.demo.model.ChatMessageEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.text.MessageFormat;
import java.time.LocalDateTime;

@Component("textChatHandler")
@Slf4j
public class TextChatHandler extends TextWebSocketHandler {

    private final ChatMessageDecoder decoder = new ChatMessageDecoder();
    private final ChatMessageEncoder encoder = new ChatMessageEncoder();

    @Resource
    private CacheManager cacheManager;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Get messages from clients
        final String payload = message.getPayload();
        final ChatMessage chatMsg = decoder.decode(payload);
        chatMsg.setFrom((String) session.getAttributes().get("username"));
        final Object token = session.getAttributes().get("username");
        log.info("server Receive {} Transmitted {}", token, payload);
        session.sendMessage(new TextMessage(MessageFormat.format("server Send to:{0} payload:{1} at:{2}",
                token, payload, LocalDateTime.now())));
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        final SessionHolder sessionHolder = SessionUtil.getSessionHolder(session);
        log.info("+++++ {} connected to server in session:{} +++++", sessionHolder.getUserId(), sessionHolder.getSessionId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // User exit, remove cache
        final SessionHolder sessionHolder = SessionUtil.getSessionHolder(session);
        final var sessionCache = cacheManager.getCache(CacheManager.CACHE_SESSION);
        sessionCache.remove(sessionHolder.getUserId());
        log.info("----- {} disconnected from server by session:{} -----", sessionHolder.getUserId(), sessionHolder.getSessionId());
    }
}
