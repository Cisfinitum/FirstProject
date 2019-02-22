package com.epam.controller;

import com.epam.model.Person;
import com.epam.model.Reservation;
import com.epam.model.ReservationStatusEnum;
import com.epam.service.PersonService;
import com.epam.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
public class ReservationController {

    private final ReservationService reservationService;
    private final PersonService personService;
    @Autowired
    public ReservationController(ReservationService reservationService, PersonService personService) {
        this.reservationService = reservationService;
        this.personService = personService;
    }
    @GetMapping("/reservation")
    public String testadmin() {
        return "redirect:/reservation/1";
    }

    @GetMapping("/reservation/{id}")
    public String listReservations(@PathVariable Integer id, ModelMap modelMap) {
        List<Reservation> reservations = reservationService.listReservations(id, reservationService.totalAmountOfRows);
        int generalAmount = reservationService.amountOfReservation();
        int amount = reservationService.totalAmountOfRows;
        modelMap.addAttribute("listReservation", reservations);
        modelMap.addAttribute("generalAmount", generalAmount);
        modelMap.addAttribute("amount", (generalAmount % amount == 0) ? generalAmount / amount : generalAmount / amount + 1);
        return "reservation";
    }

    @GetMapping("reservation/deleteReservation/{id}")
    public String deleteReservation(@PathVariable Integer id) {
        reservationService.removeReservation(id);
        return "redirect:/reservation";
    }
    @PostMapping("/reserveTour")
    public ModelAndView addReservation(@RequestParam(name = "idOfTour") Integer idOfTour,
                                       @RequestParam(name = "pricePerUnit") Integer pricePerUnit,
                                       Principal principal, ModelAndView modelAndView) {
        if(principal == null){
            modelAndView.setViewName("login");
            modelAndView.addObject("message", "Please sign in");
            return modelAndView;
        }
        String email = principal.getName();
        modelAndView.setViewName("homepage");
        Integer personId = personService.getIdByEmail(email);
        Integer numberOfPeople = 1;
        Integer totalPrice = reservationService.getTotalPrice(numberOfPeople, pricePerUnit);
        reservationService.addReservation(new Reservation(personId, idOfTour, numberOfPeople, ReservationStatusEnum.UNPAID, 1, totalPrice));
        return modelAndView.addObject("message", "Tour was reserved successfully");

    }
}