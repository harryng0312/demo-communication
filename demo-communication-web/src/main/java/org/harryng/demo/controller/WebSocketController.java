package org.harryng.demo.controller;

import org.harryng.demo.auth.service.AuthService;
import org.harryng.demo.model.ChatMessage;
import org.harryng.demo.model.OutputChatMessage;
import org.harryng.demo.model.StompPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Controller
@RequestMapping("/websocket")
public class WebSocketController {

    static Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected AuthService authService;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @RequestMapping(value = {"", "/", "/ws-basic"}, method = RequestMethod.GET)
    public String initWsBasic() {
        return "ws/ws-basic";
    }

    @RequestMapping(value = {"/ws-handler"}, method = RequestMethod.GET)
    public String initWsHandler() {
        return "ws/ws-handler";
    }

    @RequestMapping(value = "/ws-stomp", method = RequestMethod.GET)
    public String test() {
        return "ws/ws-stomp";
    }

    @MessageMapping("/ws/chat")
    @SendTo("/topic/messages")
    public OutputChatMessage send(SimpMessageHeaderAccessor headerAccessor, @Payload ChatMessage message) throws Exception {
        String time = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        return new OutputChatMessage(message.getFrom(), message.getTo(), message.getContent(), time);
    }

    @EventListener
    void handleSessionConnectedEvent(SessionConnectedEvent event) {
        // Get Accessor
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        Principal user = new StompPrincipal("");
//        sha.setUser();
    }
//    @RequestMapping(value = "/sendMsgByUser", method = RequestMethod.GET)
//    @ResponseBody
//    public Object sendMsgByUser(String token, String msg) {
//        simpMessagingTemplate.convertAndSendToUser(token, "/msg", msg);
//        return "success";
//    }
//
//    @RequestMapping(value = "/sendMsgByAll", method = RequestMethod.GET)
//    @ResponseBody
//    public Object sendMsgByAll(String msg) {
//        simpMessagingTemplate.convertAndSend("/topic", msg);
//        return "success";
//    }


}
