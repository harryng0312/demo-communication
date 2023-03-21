package org.harryng.demo.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class HttpChatInterceptor implements HandshakeInterceptor {
    static Logger logger = LoggerFactory.getLogger(HttpChatInterceptor.class);

    /**
     * Before shaking hands
     *
     * @param request
     * @param response
     * @param wsHandler
     * @param attributes
     * @return
     * @throws Exception
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        logger.info("Shake hands start");
        if (request.getURI().getQuery() != null) {
            String query = URLDecoder.decode(request.getURI().getQuery(), "utf-8");
            Map<String, String> paramMap = new HashMap<>();
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                paramMap.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
            }

            String uid = paramMap.get("username");
            if (uid != null && !uid.isEmpty()) {
                // Put in property field
                attributes.put("username", uid);
                logger.info("username " + uid + " Handshake successful!");
                return true;
            }
        }
        logger.info("User login failed");
        return false;
    }

    /**
     * After shaking hands
     *
     * @param request
     * @param response
     * @param wsHandler
     * @param exception
     */
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        logger.info("Shake hands finish");
    }

}