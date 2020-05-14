package org.harryng.demo.controller;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthSecController {
    @RequestMapping(value = {"/", "/index"})
    public String index() {
        return "auth-sec/index";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "auth-sec/admin";
    }

    @RequestMapping("/user")
    public String user() {
        return "auth-sec/user";
    }

    @RequestMapping("/logout")
    public String logout(Model model) {
        model.addAttribute("message", "Logout success!");
        return "auth-sec/index";
    }

    //    @RequestMapping(value = {"/login", "/"})
    @RequestMapping(value = {"/login"})
    public String login(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("message", "Login Failed!");
        }
        return "auth-sec/login";
    }

    @RequestMapping("/demo/{name}")
    public String demo(Model model, @PathVariable("name") @DefaultValue("") String name) {
        model.addAttribute("name", name);
        return "auth-sec/demo";
    }
}
