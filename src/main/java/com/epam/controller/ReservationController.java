package com.epam.controller;

import com.epam.model.Reservation;
import com.epam.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;

@Controller
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @GetMapping("/testadmin")
    public String listReservations(Principal user, ModelMap modelMap) {
        List<Reservation> list = reservationService.listReservations();
        modelMap.addAttribute("listReservation", list);
        return "testadmin";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable int id) {
        reservationService.removeReservation(id);
        return "redirect:/testadmin";
    }
}
