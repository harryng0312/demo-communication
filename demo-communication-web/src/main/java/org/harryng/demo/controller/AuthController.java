package org.harryng.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.auth.pojo.text.AuthenticationInfo;
import org.harryng.demo.auth.service.AuthService;
import org.harryng.demo.session.SessionHolder;
import org.harryng.demo.user.pojo.entity.UserImpl;
import org.harryng.demo.util.TextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class AuthController {

    @Resource
    protected HttpServletRequest request;

    @Resource
    protected AuthService authService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String initLogin() {
        return "auth/login";
    }

    @RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    @ResponseBody
    public String doLogin(@RequestBody String body) {
        String response = "";
        try {
            AuthenticationInfo authenticationInfo = TextUtil.jsonToObj(AuthenticationInfo.class, body);
            String username = authenticationInfo.getUsername();
            String password = authenticationInfo.getPassword();
            UserImpl user = authService.loginByUsernamePassword(username, password);
            authenticationInfo.setResult("0");
            SessionHolder.getSession(authenticationInfo.getUsername()).put(SessionHolder.K_USER, user);
            SessionHolder.getSession(authenticationInfo.getUsername()).put(SessionHolder.K_AUTH_INFO, authenticationInfo);
            response = TextUtil.objToJson(authenticationInfo);
        } catch (Exception e) {
            AuthenticationInfo authenticationInfoErr = new AuthenticationInfo();
            authenticationInfoErr.setResult("10");
            try {
                response = TextUtil.objToJson(authenticationInfoErr);
            } catch (JsonProcessingException ex) {
                log.info("", ex);
            }
            log.error("", e);
        }
        return response;
    }

    @RequestMapping(value = "/afterLogin", method = RequestMethod.GET)
    public String submitLogin(@RequestParam(name = "tokenId", defaultValue = "") String tokenId) {
        String rs = "auth/login";
        boolean result = false;
        result = SessionHolder.getSession(tokenId, false) != null;
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
