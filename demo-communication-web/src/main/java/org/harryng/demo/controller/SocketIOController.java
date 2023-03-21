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
@RequestMapping("/socketio")
public class SocketIOController {

    static Logger logger = LoggerFactory.getLogger(SocketIOController.class);

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected AuthService authService;

    @RequestMapping(value = "/socketio", method = RequestMethod.GET)
    public String initForm() {
        return "ws/socketio";
    }
}
