package org.harryng.demo.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.harryng.demo.api.auth.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/socketio")
public class SocketIOController {

    @Resource
    protected HttpServletRequest request;

    @Resource
    protected AuthService authService;

    @RequestMapping(value = "/socketio", method = RequestMethod.GET)
    public String initForm() {
        return "ws/socketio";
    }
}
