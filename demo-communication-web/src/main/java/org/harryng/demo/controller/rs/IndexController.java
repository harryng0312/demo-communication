package org.harryng.demo.controller.rs;

import org.harryng.demo.api.util.SessionHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
    @RequestMapping(value = {"/", "/index"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String index(SessionHolder sessionHolder) {
        return "index";
    }
}
