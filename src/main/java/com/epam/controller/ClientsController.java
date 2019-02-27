package com.epam.controller;

import com.epam.model.Reservation;
import com.epam.model.TourOffer;
import com.epam.service.PersonDetailsServiceImpl;
import com.epam.service.PersonService;
import com.epam.service.ReservationService;
import com.epam.service.TourOfferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

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
    public ModelAndView clientPage (Principal principal, ModelMap modelMap) {
        ModelAndView clientModel = new ModelAndView();
        Integer clientId = personService.getIdByEmail(principal.getName());
        List<Reservation> reservations = reservationService.getReservationsByPersonId(clientId);
        clientModel.addObject("person", personService.getPersonById(clientId));
        clientModel.addObject("reservations", reservations);
        clientModel.addObject("description", reservationService.getDescription(reservations, tourOfferService ));
        clientModel.setViewName("client");
        return clientModel;
    }

    @PostMapping("/clientProfile")
    public ModelAndView changePassword (@RequestParam String password, Principal principal, ModelAndView modelAndView,RedirectAttributes redirectAttributes) {
//        Pattern passwordPattern = Pattern.compile("^((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,15})$");
//        Matcher passwordMatcher = passwordPattern.matcher(password);
        Integer clientId = personService.getIdByEmail(principal.getName());
        personDetailsServiceImpl.updatePasswordById(clientId, password);
        redirectAttributes.addFlashAttribute("changepwd_status", "Password has been changed");
        modelAndView.setViewName("redirect:/clientProfile");
        return modelAndView.addObject("changepwd_status", "Password has been changed");
    }
}
