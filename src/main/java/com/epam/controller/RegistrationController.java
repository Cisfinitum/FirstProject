package com.epam.controller;

import com.epam.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    @GetMapping("/registration")
    public String getRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String postRegistrationPage(@ModelAttribute User user){
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        return "login";
    }
}
