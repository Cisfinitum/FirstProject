package com.epam.controller;

import com.epam.model.Reservation;
import com.epam.service.PersonService;
import com.epam.service.ReservationService;
import com.epam.service.TourOfferService;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.ModelMap;
        import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final PersonService personService;
    private final TourOfferService tourOfferService;

    public ReservationController(ReservationService reservationService, PersonService personService, TourOfferService tourOfferService) {
        this.reservationService = reservationService;
        this.personService = personService;
        this.tourOfferService = tourOfferService;
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
        Map<Integer, String> userInfo = personService.mapOfUsersInformation(reservations);
        Map<Integer, String> tourInfo = tourOfferService.getDescription(reservations);
        modelMap.addAttribute("listReservation", reservations);
        modelMap.addAttribute("userInfo", userInfo);
        modelMap.addAttribute("tourInfo", tourInfo);
        modelMap.addAttribute("generalAmount", generalAmount);
        modelMap.addAttribute("amount", (generalAmount % amount == 0) ? generalAmount / amount : generalAmount / amount + 1);
        return "reservation";
    }

    @GetMapping("/deleteReservation/{id}")
    public String deleteReservation(@PathVariable Integer id) {
        reservationService.removeReservation(id);
        return "redirect:/reservation";
    }
}