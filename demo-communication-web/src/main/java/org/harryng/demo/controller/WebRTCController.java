package org.harryng.demo.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.harryng.demo.auth.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/rtc")
public class WebRTCController {

    static Logger logger = LoggerFactory.getLogger(WebRTCController.class);


    @Resource
    protected HttpServletRequest request;

    @Resource
    protected AuthService authService;

    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    @RequestMapping(value = {"/rtc-web/{index}"}, method = RequestMethod.GET)
    public String initWebRtc(@PathVariable("index") String index) {
        return "webrtc/webrtc-" + index;
    }
}
