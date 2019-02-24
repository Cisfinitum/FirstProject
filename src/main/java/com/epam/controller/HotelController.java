package com.epam.controller;

import com.epam.service.HotelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/hotels")
    public String hotelPage(ModelMap modelMap) {
        return "hotels";
    }

    @PostMapping("/hotels")
    public ModelAndView addHotel(@RequestParam(name = "name") String name,
                                 @RequestParam(name = "country") String country,
                                 @RequestParam(name = "city") String city,
                                 @RequestParam(name = "stars") Integer stars,
                                 ModelAndView modelAndView) {
        return hotelService.addHotel(modelAndView, name, country, city, stars);
    }
}
