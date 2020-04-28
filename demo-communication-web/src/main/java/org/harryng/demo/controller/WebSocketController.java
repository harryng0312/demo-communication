package org.harryng.demo.controller;

import org.harryng.demo.auth.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/websocket")
public class WebSocketController {

    static Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected AuthService authService;

    @RequestMapping(value = {"", "/", "/ws-basic"}, method = RequestMethod.GET)
    public String initWsBasic() {
        return "ws/ws-basic";
    }

    @RequestMapping(value = {"/ws-handler"}, method = RequestMethod.GET)
    public String initWsHandler() {
        return "ws/ws-handler";
    }
}
