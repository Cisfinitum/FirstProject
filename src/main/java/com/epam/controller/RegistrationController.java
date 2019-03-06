package com.epam.controller;

import com.epam.model.Person;
import com.epam.model.PersonRoleEnum;
import com.epam.service.PersonDetailsServiceImpl;
import com.epam.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class RegistrationController {

    private final PersonService personService;

    @Autowired
    RegistrationController(PersonService personDetailsServiceImpl) {
        this.personService = personDetailsServiceImpl;
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "/registration";
    }

    @PostMapping("/registration")
    public ModelAndView createUserAccIfPossible(@RequestParam(name="email") String email,
                                                @RequestParam(name="password") String password,
                                                @RequestParam(name="password2") String second_password,
                                                @RequestParam(name="phoneNumber") String phoneNumber,
                                                @RequestParam(name="firstName") String firstName,
                                                @RequestParam(name="lastName") String lastName,
                                                ModelAndView modelAndView,
                                                RedirectAttributes redirectAttributes) {
        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9\\-\\._]{4,}[a-zA-Z0-9]@[a-z]{2,}\\.[a-z]{2,}$");
        Matcher emailMatcher = emailPattern.matcher(email);
        Pattern passwordPattern = Pattern.compile("^((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,15})$");
        Matcher passwordMatcher = passwordPattern.matcher(password);
        Pattern phoneNumberPattern = Pattern.compile("^[+]?[\\s\\d]{5,}$");
        Matcher phoneNumberMatcher = phoneNumberPattern.matcher(phoneNumber);
        redirectAttributes.addFlashAttribute("registration_status", "successful registration");
        if (!emailMatcher.matches()) {
            return modelAndView.addObject("message", "Email doesn't match the pattern");
        }
        else if (firstName == null || firstName.equals("")) {
            return modelAndView.addObject("message", "Name must be not empty");
        }
        else if (lastName == null || lastName.equals("")) {
            return modelAndView.addObject("message", "Name must be not empty");
        }
        else if (!phoneNumberMatcher.matches()) {
            return modelAndView.addObject("message", "Phone number is not valid");
        }
        else if (!passwordMatcher.matches()) {
            return modelAndView.addObject("message", "Password doesn't match the pattern");
        }
        else if (!password.equals(second_password)) {
            return modelAndView.addObject("message", "Passwords are not equal");
        }
        else {
            if (!personService.addPerson(email, password, PersonRoleEnum.valueOf("USER"), phoneNumber, firstName, lastName)) {
                return modelAndView.addObject("message", "Current email already exists");
            }
            else {
                modelAndView.setViewName("redirect:login");
            }
        } return modelAndView.addObject("registration_status", "Success registration");
    }
}
