package org.harryng.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.harryng.demo.auth.pojo.text.AuthenticationInfo;
import org.harryng.demo.auth.service.AuthService;
import org.harryng.demo.util.TextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.soap.Text;

@Controller
public class AuthController {

    static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected AuthService authService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String initLogin() {
        return "auth/login";
    }

    @RequestMapping(value = "/doLogin", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public String doLogin(@RequestBody String body) {
        String response = "";
        try {
            AuthenticationInfo authenticationInfo = TextUtil.jsonToObj(AuthenticationInfo.class, body);
            String username = authenticationInfo.getUsername();
            String password = authenticationInfo.getPassword();
            authService.loginByUsernamePassword(username, password);
            authenticationInfo.setResult("0");
            response = TextUtil.objToJson(authenticationInfo);
        } catch (Exception e) {
            AuthenticationInfo authenticationInfoErr = new AuthenticationInfo();
            authenticationInfoErr.setResult("10");
            try {
                response = TextUtil.objToJson(authenticationInfoErr);
            } catch (JsonProcessingException ex) {
                logger.info("", ex);
            }
            logger.error("", e);
        }
        return response;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String submitLogin(@RequestParam(name = "tokenId", defaultValue = "") String tokenId) {
        String rs = "auth/login";
        boolean result = false;
        // TODO:check tokenId is existed on cache/db?
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
