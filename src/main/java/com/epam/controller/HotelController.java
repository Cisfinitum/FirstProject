package com.epam.controller;

import com.epam.model.Hotel;
import com.epam.service.HotelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                                 ModelAndView modelAndView,
                                 RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("add_status", "successful");
        Pattern pattern = Pattern.compile("^[a-zA-Z ]*$");
        Matcher countryMatcher = pattern.matcher(country);
        Matcher cityMatcher = pattern.matcher(city);
        if (name.equals("")) {
            return modelAndView.addObject("errormessage", "Name is empty");
        }
        if (!cityMatcher.matches() || city.equals("")) {
            return modelAndView.addObject("errormessage", "Invalid name for city");
        }
        if (!countryMatcher.matches() || country.equals("")) {
            return modelAndView.addObject("errormessage", "Invalid name for country");
        }
        if (hotelService.createHotel(new Hotel(name.trim(), country.trim(), city.trim(), stars)) != 1) {
            return modelAndView.addObject("errormessage", "Some field are empty");
        }
        return modelAndView.addObject("message", "Hotel was created successfully");
    }
}
