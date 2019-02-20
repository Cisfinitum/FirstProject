package com.epam.controller;

import com.epam.model.Reservation;
import com.epam.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/testadmin")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public String testadmin() {
        return "redirect:/testadmin/1";
    }

    @GetMapping("/{id}")
    public String listReservations(@PathVariable Integer id, ModelMap modelMap) {
        List<Reservation> reservations = reservationService.listReservations(id, reservationService.totalAmountOfRows);
        int amount = reservationService.amountOfReservation();
        modelMap.addAttribute("listReservation", reservations);
        modelMap.addAttribute("generalAmount",amount);
        modelMap.addAttribute("amount", (amount / reservationService.totalAmountOfRows + 1));
        return "testadmin";
    }

    @GetMapping("/deleteReservation/{id}")
    public String deleteReservation(@PathVariable Integer id) {
        reservationService.removeReservation(id);
        return "redirect:/testadmin";
    }
}