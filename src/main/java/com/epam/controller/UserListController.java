package com.epam.controller;

import com.epam.model.Person;
import com.epam.model.Reservation;
import com.epam.model.TourOffer;
import com.epam.service.PersonDetailsServiceImpl;
import com.epam.service.PersonService;
import com.epam.service.ReservationService;
import com.epam.service.TourOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/clients")
public class UserListController {

    private final PersonService personService;
    private final ReservationService reservationService;
    private final TourOfferService tourOfferService;

    @Autowired
    public UserListController(PersonService personService, ReservationService reservationService, TourOfferService tourOfferService) {
        this.personService = personService;
        this.reservationService = reservationService;
        this.tourOfferService = tourOfferService;
    }

    @GetMapping
    public String clientsPage() {
        return "redirect:/clients/1";
    }

    @GetMapping("/{id}")
    public String listOfUsers(@PathVariable Integer id, ModelMap modelMap) {
        List<Person> clientsList = personService.listOfUsers(id, 4);
        int generalAmount = personService.amountOfUsers();
        int amount = 4;
        modelMap.addAttribute("listOfUsers", clientsList);
        modelMap.addAttribute("generalAmount", generalAmount);
        modelMap.addAttribute("amount", (generalAmount % amount == 0) ? generalAmount / amount : generalAmount / amount + 1);
        return "/clients";
    }

    @GetMapping("/addToBlackList/{id}")
    public String addToBlackList(@PathVariable Integer id) {
        personService.addToBlackList(id);
        return "redirect:/clients";
    }

    @GetMapping("/removeFromBlackList/{id}")
    public String removeFromBlackList(@PathVariable Integer id) {
        personService.removeFromBlackList(id);
        return "redirect:/clients";
    }

    @GetMapping("/clientinfo/{id}")
    public ModelAndView clientInfoPage(@PathVariable Integer id, ModelAndView modelAndView) {
        Person person = personService.getPersonById(id);
        List<Reservation> reservations = reservationService.getReservationsByPersonId(id);
        Map<Integer, String> tourInfo = tourOfferService.getDescription(reservations);
        modelAndView.addObject("person", person);
        modelAndView.addObject("description", tourInfo);
        modelAndView.addObject("reservations", reservations);
        modelAndView.setViewName("/clientinfo");
        return modelAndView;
    }

    @PostMapping("/pay/{id}")
    public ModelAndView payForTour(@PathVariable Integer id, @RequestParam(name = "reservationId") Integer reservationId, ModelAndView modelAndView, RedirectAttributes redirectAttributes){
        reservationService.changeReservationStatusById(reservationId, "PAID");
        Person person = personService.getPersonById(id);
        List<Reservation> reservations = reservationService.getReservationsByPersonId(id);
        modelAndView.addObject("person", person);
        modelAndView.addObject("reservations", reservations);
        modelAndView.addObject("paymentMessage", "Tour was approved successfully");
        modelAndView.setViewName("/clientinfo");
        return modelAndView;
    }
}
