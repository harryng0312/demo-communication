package org.harryng.demo.endpoint.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.api.constant.RequestParams;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.impl.util.SessionUtil;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

@Component("conversationInterceptor")
@Slf4j
public class ConversationInterceptor implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        // check auth here
        final String token = SessionUtil.getToken(request);
        final SessionHolder sessionHolder = SessionUtil.getSessionHolderFromAccessToken(token);
        log.info("+++++beforeHandshake:{}+++++", sessionHolder);
        if(!SessionUtil.isAnonymous(sessionHolder)){
            attributes.put(RequestParams.HEADER_SESSION_HOLDER, sessionHolder);
            return true;
        }
        return false;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        log.info("-----afterHandshake-----");
    }
}
