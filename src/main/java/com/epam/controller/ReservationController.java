package com.epam.controller;

import com.epam.model.Reservation;
import com.epam.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/testadmin")
    public String testadmin() {
        return "redirect:/testadmin/1";
    }

    @GetMapping("/testadmin/{id}")
    public String listReservations(@PathVariable int id, ModelMap modelMap) {
        List<Reservation> list = reservationService.listReservations(id, reservationService.totalAmountOfRows);
        int amount = reservationService.amountOfReservation();
        modelMap.addAttribute("listReservation", list);
        modelMap.addAttribute("generalAmount",amount);
        modelMap.addAttribute("amount", (amount / reservationService.totalAmountOfRows + 1));
        return "testadmin";
    }

    @GetMapping("testadmin/deleteReservation/{id}")
    public String deleteReservation(@PathVariable int id, ModelMap reservationModel) {
        int resp = reservationService.removeReservation(id);
        if (resp > 0) {
            reservationModel.addAttribute("message", "Reservation deletes succesfully.");
        } else {
            reservationModel.addAttribute("message", "Deletion failed.");
        }
        return "redirect:/testadmin";
    }
}