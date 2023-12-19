package org.harryng.demo.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.harryng.demo.auth.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/socketio")
public class SocketIOController {

    static Logger logger = LoggerFactory.getLogger(SocketIOController.class);

    @Resource
    protected HttpServletRequest request;

    @Resource
    protected AuthService authService;

    @RequestMapping(value = "/socketio", method = RequestMethod.GET)
    public String initForm() {
        return "ws/socketio";
    }
}
