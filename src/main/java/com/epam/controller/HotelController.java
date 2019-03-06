package com.epam.controller;

import com.epam.model.Hotel;
import com.epam.service.HotelService;
import com.epam.service.TourOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HotelController {
    private final HotelService hotelService;
    private final TourOfferService tourOfferService;

    @Autowired
    public HotelController(HotelService hotelService, TourOfferService tourOfferService) {
        this.hotelService = hotelService;
        this.tourOfferService = tourOfferService;
    }

    @GetMapping("/hotels")
    public String hotelPage(@ModelAttribute(name = "message") String message,
                            @ModelAttribute(name = "errormessage") String errormessage,
                            @ModelAttribute(name = "name") String name,
                            @ModelAttribute(name = "country") String country,
                            @ModelAttribute(name = "city") String city,
                            ModelMap modelMap) {
        List<Hotel> hotels = hotelService.getHotels();
        modelMap.addAttribute("hotels", hotels);
        modelMap.addAttribute("map", tourOfferService.getMapOfHotelUse(hotels));
        modelMap.addAttribute("message", message);
        modelMap.addAttribute("errormessage", errormessage);
        return "hotels";
    }

    @GetMapping("/deleteHotel/{id}")
    public String deleteReservation(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        if ( hotelService.deleteHotel(id) == 1){
            redirectAttributes.addFlashAttribute("message", "Hotel was deleted successfully");
        } else {
            redirectAttributes.addFlashAttribute("errormessage", "Hotel is used already, you can not delete it");
        }
        return "redirect:/hotels";
    }

    @PostMapping("/hotels")
    public String addHotel(@RequestParam(name = "name") String name,
                                 @RequestParam(name = "country") String country,
                                 @RequestParam(name = "city") String city,
                                 @RequestParam(name = "stars") Integer stars,
                                 ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
        return hotelService.addHotel(modelAndView, name, country, city, stars, redirectAttributes);
    }
}
