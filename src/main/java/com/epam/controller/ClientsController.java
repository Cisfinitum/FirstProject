package com.epam.controller;

import com.epam.model.Reservation;
import com.epam.service.PersonDetailsServiceImpl;
import com.epam.service.PersonService;
import com.epam.service.ReservationService;
import com.epam.service.TourOfferService;
import lombok.extern.slf4j.Slf4j;
import org.h2.engine.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@Slf4j
public class ClientsController {
    private final PersonDetailsServiceImpl personDetailsServiceImpl;
    private final PersonService personService;
    private final ReservationService reservationService;
    private final TourOfferService tourOfferService;

    @Autowired
    public ClientsController(PersonDetailsServiceImpl personDetailsServiceImpl, PersonService personService, ReservationService reservationService, TourOfferService tourOfferService) {
        this.personDetailsServiceImpl = personDetailsServiceImpl;
        this.personService = personService;
        this.reservationService = reservationService;
        this.tourOfferService = tourOfferService;
    }

    @GetMapping("/clientProfile")
    public ModelAndView clientPage (@ModelAttribute(name = "paymentMessage") String paymentMessage,
                                    Principal principal, ModelMap modelMap,
                                    RedirectAttributes redirectAttributes) {
        ModelAndView clientModel = new ModelAndView();
        clientModel.addObject("paymentMessage", paymentMessage);
        Integer clientId = personService.getIdByEmail(principal.getName());
        List<Reservation> reservations = reservationService.getReservationsByPersonId(clientId);
        List<String> emails = personService.listOfEmails();
        clientModel.addObject("person", personService.getPersonById(clientId));
        clientModel.addObject("reservations", reservations);
        clientModel.addObject("emails", emails);
        clientModel.addObject("description", tourOfferService.getDescription(reservations ));
        clientModel.setViewName("client");
        return clientModel;
    }

    @PostMapping("/clientProfile/change-password")
    public ModelAndView changePassword (@RequestParam(name = "password") String password,
                                        @RequestParam(name = "password2") String password2,
                                        @RequestParam(name = "previous-password") String previousPassword,
                                        Principal principal, ModelAndView modelAndView,
                                        RedirectAttributes redirectAttributes) {
        Integer clientId = personService.getIdByEmail(principal.getName());
        Pattern passwordPattern = Pattern.compile("^((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,15})$");
        Matcher passwordMatcher = passwordPattern.matcher(password);
        if (!(personService.getPersonById(clientId).getPassword().equals(previousPassword))) {

        }
        if (!passwordMatcher.matches()) {

        }
        if (!password.equals(password2)) {

        }
        personDetailsServiceImpl.updatePasswordById(clientId, password);
        redirectAttributes.addFlashAttribute("changepwd_status", "Password has been changed");
        modelAndView.setViewName("redirect:/clientProfile");
        return modelAndView.addObject("changepwd_status", "Password has been changed");
    }

    @PostMapping("/clientProfile/change-email")
    public ModelAndView changeEmail(@RequestParam(name = "email") String email,
                                    Principal principal, ModelAndView modelAndView) {
        Integer clientId = personService.getIdByEmail(principal.getName());
        Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9][a-zA-Z0-9\\-\\._]{4,}[a-zA-Z0-9]@[a-z]{2,}\\.[a-z]{2,}$");
        Matcher emailMatcher = emailPattern.matcher(email);
        if (!emailMatcher.matches()) {

        }
        personDetailsServiceImpl.updateEmailById(clientId, email);
        modelAndView.setViewName("redirect:/clientProfile");
        return modelAndView.addObject("change_status", "Email has been changed");
    }

    @PostMapping("/clientProfile/change-name")
    public ModelAndView changeName(@RequestParam(name = "first-name") String firstName,
                                    @RequestParam(name = "last-name") String lastName,
                                    Principal principal, ModelAndView modelAndView) {
        Integer clientId = personService.getIdByEmail(principal.getName());
        if (firstName == null || firstName.equals("")) {

        }
        if (lastName == null || lastName.equals("")) {

        }
        personDetailsServiceImpl.updateFirstNameById(clientId, firstName);
        personDetailsServiceImpl.updateLastNameById(clientId, lastName);
        modelAndView.setViewName("redirect:/clientProfile");
        return modelAndView.addObject("change_status", "Name has been changed");
    }

    @PostMapping("/clientProfile/change-phone-number")
    public ModelAndView changePhoneNumber(@RequestParam(name = "phone-number") String phoneNumber,
                                          Principal principal, ModelAndView modelAndView) {
        Integer clientId = personService.getIdByEmail(principal.getName());
        Pattern phoneNumberPattern = Pattern.compile("^[+]?[\\s\\d]{5,}$");
        Matcher phoneNumberMatcher = phoneNumberPattern.matcher(phoneNumber);
        modelAndView.setViewName("redirect:/clientProfile");
        return modelAndView.addObject("change_status", "Phone number has been changed");
    }
}
