package org.harryng.demo.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.aop.AuthenticationAspect;
import org.harryng.demo.api.auth.dto.AuthenticationInfo;
import org.harryng.demo.api.auth.service.AuthService;
import org.harryng.demo.api.base.dto.ResponseWrapper;
import org.harryng.demo.api.base.dto.SessionHolder;
import org.harryng.demo.api.constant.RequestParams;
import org.harryng.demo.api.util.TextUtil;
import org.harryng.demo.impl.util.SessionUtil;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class AuthController {

    @Resource
    protected HttpServletRequest request;

    @Resource
    protected AuthService authService;

    @Resource(name = "auth")
    private AuthenticationAspect auth;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String initLogin() {
        return "auth/login";
    }

    @RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public @ResponseBody String doLogin(@RequestParam("username") String username, @RequestParam("password") String password) throws Exception {
        final AuthenticationInfo authenticationInfo = authService.loginByUsernamePassword(username, password);
        final ResponseWrapper<AuthenticationInfo> res = ResponseWrapper.<AuthenticationInfo>builder().data(authenticationInfo).build();
        return TextUtil.objToJson(res);
    }

    @RequestMapping(value = "/afterLogin", method = RequestMethod.GET)
    public String doAfterLogin(@RequestParam(name = RequestParams.PARAM_ACCESS_TOKEN, defaultValue = "") String token) {
        return "auth/login";
//        boolean result = false;
//        result = SessionHolder.getSession(tokenId, false) != null;
//        if (result) {
//            rs = String.format("redirect:%s", "welcome");
//        }
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome() throws Exception {
        final SessionHolder sessionHolder = SessionUtil.getSessionHolderFromAccessToken(request.getHeader(RequestParams.HEADER_AUTHORIZATION),
                request.getHeader(RequestParams.PARAM_ACCESS_TOKEN));
//        if(!SessionHolder.ANONYMOUS.getUserId().equals(sessionHolder.getUserId())){
        if(SessionUtil.isAnonymous(sessionHolder)) {
            return "auth/welcome";
        }
        return "auth/login";
    }
}
