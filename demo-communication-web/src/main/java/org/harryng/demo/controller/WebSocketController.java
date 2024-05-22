package org.harryng.demo.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.harryng.demo.impl.auth.service.AuthService;
import org.harryng.demo.model.ChatMessage;
import org.harryng.demo.model.OutputChatMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

@Controller
@RequestMapping("/websocket")
public class WebSocketController {

    static Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    @Resource
    protected HttpServletRequest request;

    @Resource
    protected AuthService authService;

//    @Resource
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
    public String initWsStomp() {
        return "ws/ws-stomp";
    }

    @RequestMapping(value = "/ws-stomp2", method = RequestMethod.GET)
    public String initWsStomp2() {
        return "ws/ws-stomp2";
    }

    @RequestMapping(value = "/ws-stomp/{name}", method = RequestMethod.GET)
    public String initWsStompAuth(@PathVariable("name") String name) {
        return "ws/ws-stomp-" + name;
    }

    @EventListener(SessionConnectedEvent.class)
    protected void handleSessionConnectedEvent(SessionConnectedEvent event) {
        // Get Accessor
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
//        Principal user = new StompPrincipal("");
//        sha.setUser();
    }

    @EventListener(SessionConnectEvent.class)
    protected void handleSessionConnectEvent(SessionConnectEvent event){
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        String username = sha.getFirstNativeHeader("user");
        logger.info("User " + username + " connected");
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, "", new ArrayList<>());
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
    }

    @EventListener(SessionDisconnectEvent.class)
    protected void handleSessionDisconnectEvent(SessionDisconnectEvent event) {
        // Get Accessor
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
    }

    @MessageMapping("/ws/chat")
    @SendTo("/topic/messages")
    public OutputChatMessage send(SimpMessageHeaderAccessor headerAccessor, @Payload ChatMessage message) throws Exception {
        String time = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        return new OutputChatMessage(message.getFrom(), message.getTo(), message.getContent(), time);
    }

//    @MessageMapping("/ws/chat/user")
//    @SendTo("/topic/messages")
    public void sendToUser(SimpMessageHeaderAccessor headerAccessor, @Payload ChatMessage message) throws Exception {
        String time = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        String to = message.getTo();
        OutputChatMessage oMsg = new OutputChatMessage(message.getFrom(), message.getTo(), message.getContent(), time);
        String dest = String.format("/topic/messages/%s", to);
        simpMessagingTemplate.convertAndSend(dest, oMsg);
    }

    @MessageMapping("/ws/chat/user")
//    @SendToUser("/topic/messages")
    public ChatMessage sendToUserAuth(SimpMessageHeaderAccessor headerAccessor, @Payload ChatMessage message) throws Exception {
        String time = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        String to = message.getTo();
        OutputChatMessage oMsg = new OutputChatMessage(message.getFrom(), message.getTo(), message.getContent(), time);
        String origin = String.format("/topic/messages/%s", message.getFrom());
        String dest = String.format("/topic/messages/%s", to);
        simpMessagingTemplate.convertAndSend(origin, oMsg);
        simpMessagingTemplate.convertAndSend(dest, oMsg);
        return oMsg;
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
