package com.epam.controller;

import com.epam.model.Person;
import com.epam.model.Reservation;
import com.epam.service.PersonDetailsServiceImpl;
import com.epam.service.PersonService;
import com.epam.service.ReservationService;
import com.epam.service.TourOfferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
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
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public ClientsController(PersonDetailsServiceImpl personDetailsServiceImpl,
                             PersonService personService,
                             ReservationService reservationService,
                             TourOfferService tourOfferService,
                             BCryptPasswordEncoder passwordEncoder) {
        this.personDetailsServiceImpl = personDetailsServiceImpl;
        this.personService = personService;
        this.reservationService = reservationService;
        this.tourOfferService = tourOfferService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/clientProfile")
    public ModelAndView clientPage (@ModelAttribute(name = "paymentMessage") String paymentMessage,
                                    @ModelAttribute (name = "cancelMessage") String cancelMessage, Principal principal,
                                    RedirectAttributes redirectAttributes) {
        ModelAndView clientModel = new ModelAndView();
        if (redirectAttributes != null) {
            for (String string: redirectAttributes.getFlashAttributes().keySet())
                clientModel.addObject(string, redirectAttributes.getFlashAttributes().get(string));
        }
        clientModel.addObject("paymentMessage", paymentMessage);
        clientModel.addObject("cancelMessage", cancelMessage);
        Integer clientId = personService.getIdByEmail(principal.getName());
        List<Reservation> reservations = reservationService.getReservationsByPersonId(clientId);
        clientModel.addObject("passwordEncoder", passwordEncoder);
        clientModel.addObject("person", personService.getPersonById(clientId));
        clientModel.addObject("reservations", reservations);
        clientModel.addObject("description", tourOfferService.getDescription(reservations ));
        clientModel.setViewName("client");
        return clientModel;
    }

    @PostMapping("/clientProfile")
    public ModelAndView changePassword (@RequestParam(name="password") String password, @RequestParam(name="previous-password") String previousPassword,
                                        Principal principal, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
        Pattern passwordPattern = Pattern.compile("^((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,15})$");
        Matcher passwordMatcher = passwordPattern.matcher(password);
        Integer clientId = personService.getIdByEmail(principal.getName());
        Person person = personService.getPersonById(clientId);
        if (!passwordMatcher.matches()) {
            redirectAttributes.addFlashAttribute("changeStatus", "Password must contain at least 1 digit one up-case letter \n and one low-case");
            modelAndView.setViewName("redirect:/clientProfile");
            return modelAndView;
        }
        if (!passwordEncoder.matches(previousPassword, person.getPassword())){
            redirectAttributes.addFlashAttribute("changeStatus", "Previous password is incorrect");
            modelAndView.setViewName("redirect:/clientProfile");
            return modelAndView;
        }
        personDetailsServiceImpl.updatePasswordById(clientId, passwordEncoder.encode(password));
        redirectAttributes.addFlashAttribute("changeStatus", "Successful registration");
        modelAndView.setViewName("redirect:/clientProfile");
        return modelAndView;
    }
}
