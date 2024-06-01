package org.harryng.demo.endpoint.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.api.constant.RequestParams;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.api.util.TextUtil;
import org.harryng.demo.endpoint.event.ConversionMessageEvent;
import org.harryng.demo.impl.cache.CachesManager;
import org.harryng.demo.impl.conversation.dto.AbstractMessage;
import org.harryng.demo.api.util.SessionUtil;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.text.MessageFormat;

@Component("textChatHandler")
@RequiredArgsConstructor
@Slf4j
public class TextChatHandler extends TextWebSocketHandler {
//    private final ChatMessageDecoder decoder = new ChatMessageDecoder();
//    private final ChatMessageEncoder encoder = new ChatMessageEncoder();

    private final ApplicationEventPublisher appEventPublisher;
    private final CachesManager cachesManager;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Get messages from clients
        final String payload = message.getPayload();
        final SessionHolder sessionHolder = (SessionHolder) session.getAttributes().getOrDefault(RequestParams.HEADER_SESSION_HOLDER, SessionHolder.ANONYMOUS);
        if (SessionUtil.isAuthenticated(sessionHolder)) {
//            final UUID msgId = UUID.randomUUID();
//            final String token = SessionUtil.getToken(session.getHandshakeHeaders());
//            log.info("server Receive:{}", payload);
            final var sentMsg = TextUtil.jsonToObj(org.harryng.demo.impl.conversation.dto.TextMessage.class, payload);
            log.info("===== Server received:{}", sentMsg);
            // send signal to sender
            final var strSentMsg = TextUtil.objToJson(sentMsg);
            final var signalMsg = new org.harryng.demo.impl.conversation.dto.TextMessage();
            signalMsg.setSenderId("0");
            signalMsg.setRecipientId(String.valueOf(sessionHolder.getUserId()));
            signalMsg.setRecipientType(AbstractMessage.TYPE_RECIPIENT_IND);
            signalMsg.setType(AbstractMessage.TYPE_MSG_SIGNAL);
            signalMsg.setContent(MessageFormat.format("Message {0} is sent", sentMsg.getId()));
            final var strSignal = TextUtil.objToJson(signalMsg);
            // send msg to recipient
            appEventPublisher.publishEvent(new ConversionMessageEvent(this, strSentMsg));
            // resend signal to sender
            session.sendMessage(new TextMessage(strSignal));
        } else {
            log.info("Drop message {}", payload);
        }
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
        final var sessionCache = cachesManager.getCache(CachesManager.CACHE_SESSION);
        sessionCache.remove(sessionHolder.getUserId());
        log.info("----- {} disconnected from server by session:{} -----", sessionHolder.getUserId(), sessionHolder.getSessionId());
    }
}
