package com.epam.controller;

import com.epam.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;

@Controller
@RequestMapping("/")
public class MainController {


    private final PersonService personsService;

    @Autowired
    public MainController(PersonService personsService) {
        this.personsService = personsService;
    }

    @GetMapping("/")
    public String mainPage() {
        return "login";
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

    @GetMapping("/403")
    public String badRequest(Principal user, ModelMap modelMap) {
        return "403";
    }
}