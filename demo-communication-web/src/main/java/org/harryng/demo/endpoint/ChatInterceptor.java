package org.harryng.demo.endpoint;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class ChatInterceptor implements HandshakeInterceptor {

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
        System.out.println("Shake hands start");
        // Get request parameters
//        Map<String, String> paramMap = HttpUtil.decodeParamMap(request.getURI().getQuery(), "utf-8");
        String query = URLDecoder.decode(request.getURI().getQuery(), "utf-8");
        Map<String, String> paramMap = new HashMap<>();
        String[] pairs = query.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf("=");
            paramMap.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
        }

        String uid = paramMap.get("token");
        if (uid != null && !uid.isEmpty()) {
            // Put in property field
            attributes.put("token", uid);
            System.out.println("user token " + uid + " Handshake successful!");
            return true;
        }
        System.out.println("User login failed");
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
        System.out.println("Shake hands finish");
    }

}