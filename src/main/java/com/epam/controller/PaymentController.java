package com.epam.controller;

import com.epam.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
public class PaymentController {
    private final ReservationService reservationService;

    public PaymentController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/pay")
    public ModelAndView payForTour(@RequestParam(name = "reservationId") Integer reservationId, ModelAndView modelAndView, RedirectAttributes redirectAttributes){
        reservationService.changeReservationStatusById(reservationId);
        redirectAttributes.addFlashAttribute("paymentMessage", "Tour was paid successfully");
        modelAndView.setViewName("redirect:/clientProfile");
        return modelAndView;
    }
    @PostMapping("/payLater")
    public ModelAndView payForTour(ModelAndView modelAndView, RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("paymentMessage", "Tour was reserved, but not paid.");
        modelAndView.setViewName("redirect:/clientProfile");
        return modelAndView;
    }

    @GetMapping("/payment")
    public ModelAndView payForTour(@ModelAttribute("reservationId") Integer reservationId,
                                   @ModelAttribute("totalPrice") Integer totalPrice,
                                   @ModelAttribute("pricePerUnit") Integer pricePerUnit,
                                   @ModelAttribute("numberOfPeople") Integer numberOfPeople,
                                   @ModelAttribute("discountId") Integer discountId,
                                   ModelAndView modelAndView){
        modelAndView.addObject("reservationId", reservationId);
        modelAndView.addObject("totalPrice", totalPrice);
        modelAndView.addObject("pricePerUnit", pricePerUnit);
        modelAndView.addObject("numberOfPeople", numberOfPeople);
        modelAndView.addObject("discountId", discountId);
        modelAndView.setViewName("payment");
        return modelAndView;
    }
}
