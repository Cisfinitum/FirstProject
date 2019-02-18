package com.epam.controller;

import com.epam.model.Person;
import com.epam.service.PersonDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class RegistrationController {

    private final PersonDetailsServiceImpl personDetailsServiceImpl;

    @Autowired
    RegistrationController(PersonDetailsServiceImpl personDetailsServiceImpl) {
        this.personDetailsServiceImpl = personDetailsServiceImpl;
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "/registration";
    }

    @PostMapping("/registration")
    public String createUserAccIfPossible(@RequestParam(name="email") String email,
                                          @RequestParam(name="password") String password) {
        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9\\-\\._]{4,}[a-zA-Z0-9]@[a-z]{2,}\\.[a-z]{2,}$");
        Matcher emailMatcher = emailPattern.matcher(email);
        if (!emailMatcher.matches()) return "/registration";
        Pattern passwordPattern = Pattern.compile("^((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,15})$");
        Matcher passwordMatcher = passwordPattern.matcher(password);
        if(!passwordMatcher.matches()) return "/registration";
        if (personDetailsServiceImpl.addPersonToDataBase(new Person(email, password, "USER"))) return "/login";
        else return "/registration";
    }
}
