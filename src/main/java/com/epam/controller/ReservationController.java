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

    @GetMapping("/reservation")
    public String listReservations(ModelMap modelMap) {
        List<Reservation> list = reservationService.listReservations();
        modelMap.addAttribute("listReservation", list);
        return "reservation";
    }

    @GetMapping("/deleteReservation/{id}")
    public String deleteReservation(@PathVariable int id, ModelMap reservationModel) {
        int resp =  reservationService.removeReservation(id);
        if(resp > 0 ) {
            reservationModel.addAttribute("message", "Reservation deletes succesfully.");
        } else {
            reservationModel.addAttribute("message", "Deletion failed.");
        }
        return "redirect:/reservation";
    }
}