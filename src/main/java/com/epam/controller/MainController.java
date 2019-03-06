package com.epam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping("/")
    public String mainPage() {
        return "homepage";
    }

    @GetMapping("/homepage")
    public String homePage () {
        return "/homepage";
    }

    @GetMapping("/testuser")
    public String testUser() {
        return "testuser";
    }

    @GetMapping("/testpage")
    public String testPage() {
        return "testpage";
    }

    @GetMapping("/index")
    public String index() {
        return "homepage";
    }

    @GetMapping("/contacts")
    public String contacts() {
        return "contacts";
    }

    @GetMapping("/403")
    public String badRequest() {
        return "403";
    }

    @GetMapping("/login")
    public String loginPage (ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
        if (redirectAttributes != null) {
            for (String string: redirectAttributes.getFlashAttributes().keySet())
            modelAndView.addObject(string, redirectAttributes.getFlashAttributes().get(string));
        }
        return "/login";
    }

    @GetMapping("/access-denied")
    public String accessDeniedPage () {
        return "/access-denied";
    }

    @GetMapping("/404")
    public String notFoundPage() {
        return "404";
    }

    @GetMapping("/info")
    public String info() {
        return "info";
    }


}