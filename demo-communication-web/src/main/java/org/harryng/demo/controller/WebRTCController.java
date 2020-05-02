package org.harryng.demo.controller;

import org.harryng.demo.auth.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
