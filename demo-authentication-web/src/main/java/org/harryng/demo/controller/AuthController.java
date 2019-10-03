package org.harryng.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {

    @Autowired
    protected HttpServletRequest request;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String initLogin() {
        return "auth/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String submitLogin(@RequestBody String body) {
        String rs = "auth/login";
        boolean result = false;

        if (result) {
            rs = String.format("redirect:%s", "welcome");
        }
        return rs;
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome() {
        return "auth/welcome";
    }
}
