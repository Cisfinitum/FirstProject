package com.epam.controller;

import com.epam.model.Reservation;
import com.epam.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String testadmin() {
        return "redirect:/reservation/1";
    }

    @GetMapping("/{id}")
    public String listReservations(@PathVariable Integer id, ModelMap modelMap) {
        List<Reservation> reservations = reservationService.listReservations(id, reservationService.totalAmountOfRows);
        int generalAmount = reservationService.amountOfReservation();
        int amount = reservationService.totalAmountOfRows;
        modelMap.addAttribute("listReservation", reservations);
        modelMap.addAttribute("generalAmount", generalAmount);
        modelMap.addAttribute("amount", (generalAmount % amount == 0) ? generalAmount / amount : generalAmount / amount + 1);
        return "reservation";
    }

    @GetMapping("/deleteReservation/{id}")
    public String deleteReservation(@PathVariable Integer id) {
        reservationService.removeReservation(id);
        return "redirect:/reservation";
    }

    @PostMapping("/homepage")
    public ModelAndView addReservation(@RequestParam(name = "idOfTour") Integer idOfTour,
                                       @RequestParam(name = "numberOfPeople") Integer numberOfPeople,
                                       @RequestParam(name = "pricePerUnit") Integer pricePerUnit, Principal principal, ModelAndView modelAndView) {
        String name = principal.getName();
        reservationService.addReservation(new Reservation());
        return modelAndView.addObject("message", "Tour was reserved successfully");
    }

}