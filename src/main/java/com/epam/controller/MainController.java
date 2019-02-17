package com.epam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping("/")
    public String mainPage() {
        return "homepage";
    }

    @GetMapping("/testuser")
    public String testUser(Principal user, ModelMap modelMap) {
        return "testuser";
    }

    @GetMapping("/testadmin")
    public String testAdmin(Principal user, ModelMap modelMap) {
        return "testadmin";
    }

    @GetMapping("/testpage")
    public String testPage(Principal user, ModelMap modelMap) {
        return "testpage";
    }

    @GetMapping("/index")
    public String index(Principal user, ModelMap modelMap) {
        return "homepage";
    }

    @GetMapping("/403")
    public String badRequest(Principal user, ModelMap modelMap) {
        return "403";
    }

    @GetMapping("/login")
    public String loginPage (Principal user, ModelMap modelMap) {
        return "/login";
    }

    @GetMapping("/homepage")
    public String homePage (Principal user, ModelMap modelMap) {
        return "/homepage";
    }
}