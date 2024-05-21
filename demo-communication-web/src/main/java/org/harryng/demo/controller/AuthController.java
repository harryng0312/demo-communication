package org.harryng.demo.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.harryng.demo.aop.AuthenticationAspect;
import org.harryng.demo.impl.auth.dto.AuthenticationInfo;
import org.harryng.demo.impl.auth.service.AuthService;
import org.harryng.demo.impl.base.dto.ResponseWrapper;
import org.harryng.demo.api.util.SessionHolder;
import org.harryng.demo.api.constant.RequestParams;
import org.harryng.demo.impl.user.service.UserService;
import org.harryng.demo.api.util.TextUtil;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
//@Path("")
@Slf4j
public class AuthController {

    @Resource
    protected HttpServletRequest request;
    @Resource
    protected AuthService authService;
    @Resource(name = "auth")
    private AuthenticationAspect auth;
    @Resource
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    @GET
//    @Path("/login")
    public String initLogin() {
        return "auth/login";
    }

    @RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
//    @POST
//    @Path("/login")
//    @Consumes(MediaType.APPLICATION_JSON_VALUE)
//    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String doLogin(@RequestParam("username") String username, @RequestParam("password") String password) throws Exception {
//    public Response doLogin(@FormParam("username") String username, @FormParam("password") String password) throws Exception {
        final AuthenticationInfo authenticationInfo = authService.loginByUsernamePassword(username, password);
        final ResponseWrapper<AuthenticationInfo> res = ResponseWrapper.<AuthenticationInfo>builder().data(authenticationInfo).build();
        return TextUtil.objToJson(res);
//        return Response.ok().entity(TextUtil.objToJson(res)).build();
    }

    @RequestMapping(value = "/afterLogin", method = RequestMethod.GET)
//    @GET
//    @Path("/after-login")
    public String doAfterLogin(@RequestParam(name = RequestParams.PARAM_ACCESS_TOKEN, defaultValue = "") String token) {
//    public String doAfterLogin(@QueryParam(RequestParams.PARAM_ACCESS_TOKEN) @DefaultValue("") String token) {
        return "auth/login";
//        boolean result = false;
//        result = SessionHolder.getSession(tokenId, false) != null;
//        if (result) {
//            rs = String.format("redirect:%s", "welcome");
//        }
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
//    @GET
//    @Path("/welcome")
    public String welcome(Model model) throws Exception {
//        final SessionHolder sessionHolder = SessionUtil.getSessionHolderFromAccessToken(
//                request.getHeader(RequestParams.HEADER_AUTHORIZATION),
//                request.getHeader(RequestParams.PARAM_ACCESS_TOKEN));
//        if(!SessionUtil.isAnonymous(sessionHolder)) {
        final SessionHolder sessionHolder = SessionHolder.builder().build();
        final var userImpl = userService.getByMyId(sessionHolder, Map.of());
        if(userImpl.isPresent()) {
            userImpl.ifPresent(user -> model.addAttribute("user", user));
            return "auth/welcome";
        }
//        }
        return "auth/login";
    }
}
