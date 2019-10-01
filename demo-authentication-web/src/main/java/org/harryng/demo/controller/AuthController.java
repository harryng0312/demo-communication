package org.harryng.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthController {
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String initLogin() {
        return "auth/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String submitLogin() {
        String rs = "auth/login";
        boolean result = false;
        if (result) {
            rs = String.format("redirect:%s", "user/welcome");
        }
        return rs;
    }
}
