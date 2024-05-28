package org.harryng.demo.controller.rs;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.harryng.demo.impl.auth.service.AuthService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.MessageFormat;

@Controller
@RequestMapping("/rtc")
public class WebRTCController {

    @Resource
    protected HttpServletRequest request;

    @Resource
    protected AuthService authService;

//    @Resource
    private SimpMessagingTemplate simpMessagingTemplate;

    @RequestMapping(value = {"/rtc-web/{index}"}, method = RequestMethod.GET)
    public String initWebRtc(@PathVariable("index") String index) {
//        return "webrtc/webrtc-" + index;
        return MessageFormat.format("webrtc/webrtc-{0}", index);
    }
}
