package org.harryng.demo.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class WsSessionManager {
    static Logger logger = LoggerFactory.getLogger(WsSessionManager.class);
    private static ConcurrentMap<String, WebSocketSession> SESSION_POOL = new ConcurrentHashMap<>();

    public static void add(String key, WebSocketSession session) {
        // Add session
        SESSION_POOL.put(key, session);
    }

    public static WebSocketSession remove(String key) {
        // Delete session
        return SESSION_POOL.remove(key);
    }

    public static void removeAndClose(String key) {
        WebSocketSession session = remove(key);
        if (session != null) {
            try {
                // Close connection
                session.close();
            } catch (IOException e) {

            }
        }
    }

    public static WebSocketSession get(String key) {
        // Get session
        return SESSION_POOL.get(key);
    }
}
