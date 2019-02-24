package com.epam.controller;

import com.epam.model.Hotel;
import com.epam.model.TourOffer;
import com.epam.service.HotelService;
import com.epam.service.ReservationService;
import com.epam.service.TourOfferService;
import com.epam.validator.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@Slf4j
public class ToursController {
    private final TourOfferService toursOfferService;
    private final HotelService hotelService;
    private final ReservationService reservationService;

    @Autowired
    ToursController(TourOfferService toursOfferService, HotelService hotelService, ReservationService reservationService) {
        this.toursOfferService = toursOfferService;
        this.hotelService = hotelService;
        this.reservationService = reservationService;
    }

    @GetMapping("/listoftours")
    public ModelAndView getToursList() {
        ModelAndView toursModel = new ModelAndView();
        List<Hotel> listOfHotels = hotelService.getHotels();
        Map<Integer, Hotel> hotels = new HashMap<>();
        for(Hotel hotel: listOfHotels){
            hotels.put(hotel.getId(),hotel);
        }
        toursModel.addObject("listOfTours", toursOfferService.getTours());
        toursModel.addObject("hotels", hotels);
        toursModel.setViewName("tours");
        return toursModel;
    }

    @PostMapping("/searchtours")
    public ModelAndView searchTours(@RequestParam String country, @RequestParam String startDate,
                                    @RequestParam String endDate, @RequestParam String numberOfPeople) {
        ModelAndView toursModel = new ModelAndView();
        toursModel.setViewName("homepage");
        try {
            LocalDate addStartDate = Validator.getDate(startDate, true);
            LocalDate addEndDate = Validator.getDate(endDate, true);
            toursModel.addObject("list", toursOfferService.searchTours(null, addStartDate, addEndDate));
            return toursModel;
        } catch (Exception e) {
            toursModel.addObject("error", e.getMessage());
            return toursModel;
        }
    }

    @PostMapping("/deletetour")
    public ModelAndView deleteTour(@RequestParam String idOfTour) {
        ModelAndView toursModel = new ModelAndView();
        if (toursOfferService.deleteTour(Integer.valueOf(idOfTour)) == 1) {
            toursModel.addObject("result", "Success");
        } else {
            toursModel.addObject("error", "Failed to delete");
        }
        toursModel.setViewName("redirect:/listoftours");
        return toursModel;
    }

    @PostMapping("/addtour")
    public ModelAndView addTour(@RequestParam String tourType, @RequestParam String startDate, @RequestParam String endDate,
                                @RequestParam String country, @RequestParam String city, @RequestParam String hotel,
                                @RequestParam String pricePerPerson, @RequestParam String discount, @RequestParam String tourDescription) {
        ModelAndView toursModel = new ModelAndView();
        toursModel.setViewName("addtour");
        try {
            LocalDate addStartDate = Validator.getDate(startDate, false);
            LocalDate addEndDate = Validator.getDate(endDate, false);
            Integer addPricePerPerson = Validator.getInt(pricePerPerson);
            Validator.checkEmpty(tourType);
            Validator.checkEmpty(tourDescription);
            Validator.checkDateDifferent(addStartDate, addEndDate);
            int result = toursOfferService.addTour(TourOffer.builder()
                    .id(1)
                    .tourType(tourType)
                    .startDate(addStartDate)
                    .endDate(addEndDate)
                    .pricePerUnit(addPricePerPerson)
                    .hotelId(1) //stub
                    .description(tourDescription)
                    .discountId(1) //stub
                    .build());

            if (result == 1) {
                toursModel.addObject("result", "Success");
            } else {
                toursModel.addObject("error", "Failed to add");
            }
            return toursModel;

        } catch (Exception e) {
            toursModel.addObject("error", e.getMessage());
            return toursModel;
        }
    }

    @PostMapping("/updatetour")
    public ModelAndView updateTour(@RequestParam String tourId, @RequestParam String tourType, @RequestParam String startDate, @RequestParam String endDate,
                                   @RequestParam String country, @RequestParam String city, @RequestParam String hotel,
                                   @RequestParam String pricePerPerson, @RequestParam String discount, @RequestParam String tourDescription) {
        ModelAndView toursModel = new ModelAndView();
        toursModel.setViewName("updatetour");
        try {
            LocalDate addStartDate = Validator.getDate(startDate, false);
            LocalDate addEndDate = Validator.getDate(endDate, false);
            Integer addPricePerPerson = Validator.getInt(pricePerPerson);
            Validator.checkEmpty(tourType);
            Validator.checkEmpty(tourDescription);
            Validator.checkDateDifferent(addStartDate, addEndDate);
            int result = toursOfferService.updateTour(TourOffer.builder()
                    .id(Integer.valueOf(tourId))
                    .tourType(tourType)
                    .startDate(addStartDate)
                    .endDate(addEndDate)
                    .pricePerUnit(addPricePerPerson)
                    .hotelId(1) //stub
                    .description(tourDescription)
                    .discountId(1) //stub
                    .build());
            if (result == 1) {
                toursModel.addObject("result", "Success");
            } else {
                toursModel.addObject("error", "Failed to add");
            }
            return toursModel;
        } catch (Exception e) {
            toursModel.addObject("error", e.getMessage());
            return toursModel;
        }
    }

    @GetMapping("/addtour")
    public String getAddTour() {
        return "addtour";
    }
}
