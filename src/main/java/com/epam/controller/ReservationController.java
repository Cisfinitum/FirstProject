package com.epam.controller;

import com.epam.model.Reservation;
import com.epam.service.ReservationService;
import com.epam.service.TourOfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.epam.service.PersonService;
import com.epam.service.TourOfferService;


import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final PersonService personService;
    private final TourOfferService tourOfferService;

    @Autowired
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
    public String listReservations(@PathVariable Integer id, @ModelAttribute(name = "message") String message,
                                   @ModelAttribute(name = "errormessage") String errormessage, ModelMap modelMap) {
        int amount = reservationService.totalAmountOfRows;
        List<Reservation> reservations = reservationService.listReservations(id, amount, "NEW");
        List<Reservation> archiveReservations = reservationService.listReservations(1, reservationService.amountOfReservation("ARCHIVED"), "ARCHIVED");
        Map<Integer, String> userInfo = personService.mapOfUsersInformation(reservations);
        Map<Integer, String> tourInfo = tourOfferService.getDescription(reservations);
        Map<Integer, String> archiveUserInfo = personService.mapOfUsersInformation(archiveReservations);
        Map<Integer, String> archiveTourInfo = tourOfferService.getDescription(archiveReservations);
        int generalAmount = reservationService.amountOfReservation("NEW");
        modelMap.addAttribute("listReservation", reservations);
        modelMap.addAttribute("userInfo", userInfo);
        modelMap.addAttribute("tourInfo", tourInfo);
        modelMap.addAttribute("archiveUserInfo", archiveUserInfo);
        modelMap.addAttribute("archiveTourInfo", archiveTourInfo);
        modelMap.addAttribute("listArchiveReservation", archiveReservations);
        modelMap.addAttribute("generalAmount", generalAmount);
        modelMap.addAttribute("amount", (generalAmount % amount == 0) ? generalAmount / amount : generalAmount / amount + 1);
        modelMap.addAttribute("message", message);
        modelMap.addAttribute("errormessage", errormessage);
        return "reservation";
    }

    @GetMapping("/deleteReservation/{id}")
    public String deleteReservation(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        if (reservationService.removeReservation(id) == 1) {
            redirectAttributes.addFlashAttribute("message", "Reservation was deleted successfully");
        } else {
            redirectAttributes.addFlashAttribute("errormessage", "Reservation was not deleted");
        }
        return "redirect:/reservation";
    }

    @GetMapping("/archiveReservation/{id}")
    public String archiveReservation(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        if (reservationService.changeArchiveStatusById(id, "ARCHIVED") == 1) {
            redirectAttributes.addFlashAttribute("message", "Reservation was archived successfully");
        } else {
            redirectAttributes.addFlashAttribute("errormessage", "Reservation was not archived");
        }
        return "redirect:/reservation";
    }

    @GetMapping("/restoreReservation/{id}")
    public String restoreReservation(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        if(reservationService.changeArchiveStatusById(id, "NEW") == 1) {
            redirectAttributes.addFlashAttribute("message", "Reservation was restored successfully");
        } else {
            redirectAttributes.addFlashAttribute("errormessage", "Reservation was not restored");
        }
        return "redirect:/reservation";
    }

    @GetMapping("/cleanArchive")
    public String cleanArchive(RedirectAttributes redirectAttributes){
        if(reservationService.cleanArchive() > 0){
            redirectAttributes.addFlashAttribute("message", "Archive was cleaned");
        } else {
            redirectAttributes.addFlashAttribute("errormessage", "Nothing to clean");
        }
        return "redirect:/reservation";
    }
}