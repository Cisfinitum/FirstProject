package com.epam.controller;

import com.epam.model.Reservation;
import com.epam.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/testadmin")
    public String listReservations(ModelMap modelMap) {
        List<Reservation> list = reservationService.listReservations();
        modelMap.addAttribute("listReservation", list);
        return "testadmin";
    }

    @GetMapping("/deleteReservation/{id}")
    public String deleteReservation(@PathVariable int id) {
        reservationService.removeReservation(id);
        return "redirect:/testadmin";
    }
}
