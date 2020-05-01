package org.harryng.demo.controller;

import org.harryng.demo.auth.service.AuthService;
import org.harryng.demo.model.ChatMessage;
import org.harryng.demo.model.OutputChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
@RequestMapping("/rtc")
public class WebRTCController {

    static Logger logger = LoggerFactory.getLogger(WebRTCController.class);

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected AuthService authService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @RequestMapping(value = {"/rtc-web/{index}"}, method = RequestMethod.GET)
    public String initWebRtc(@PathVariable("index") String index) {
        return "webrtc/webrtc-" + index;
    }

}
