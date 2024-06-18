package org.harryng.demo.endpoint.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.api.util.SessionUtil;
import org.harryng.demo.endpoint.event.ConversionMessageEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component("textChatHandler")
@RequiredArgsConstructor
@Slf4j
public class TextChatHandler extends TextWebSocketHandler {
//    private final ChatMessageDecoder decoder = new ChatMessageDecoder();
//    private final ChatMessageEncoder encoder = new ChatMessageEncoder();

    private final ApplicationEventPublisher appEventPublisher;
//    private final CachesManager cachesManager;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
//        log.info("===== Received message: {}", message.getPayload());
        appEventPublisher.publishEvent(new ConversionMessageEvent(session, message.getPayload()));
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
//        final var sessionCache = cachesManager.getCache(CachesManager.CACHE_SESSION);
//        sessionCache.remove(sessionHolder.getUserId());
        log.info("----- {} disconnected from server by session:{} -----", sessionHolder.getUserId(), sessionHolder.getSessionId());
    }
}
