package com.epam.controller;

import com.epam.model.Hotel;
import com.epam.model.TourOffer;
import com.epam.service.HotelService;
import com.epam.service.PersonService;
import com.epam.service.ReservationService;
import com.epam.service.TourOfferService;
import com.epam.validator.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


@Controller
@Slf4j
public class ToursController {
    private final TourOfferService toursOfferService;
    private final HotelService hotelService;
    private final ReservationService reservationService;
    private final PersonService personService;

    @Autowired
    public ToursController(TourOfferService toursOfferService, ReservationService reservationService, PersonService personService, HotelService hotelService) {
        this.toursOfferService = toursOfferService;
        this.hotelService = hotelService;
        this.reservationService = reservationService;
        this.personService = personService;
    }

    @GetMapping("/listoftours")
    public ModelAndView getToursList() {
        ModelAndView toursModel = new ModelAndView();
        toursModel.addObject("listOfTours", toursOfferService.getTours());
        toursModel.addObject("hotels", hotelService.getMapOfHotels());
        toursModel.setViewName("tours");
        return toursModel;
    }

    @PostMapping("/searchtours/{id}")
    public ModelAndView searchTours(@PathVariable Integer id, @RequestParam String country, @RequestParam String startDate,
                                    @RequestParam String endDate, @RequestParam String numberOfPeople) {
        ModelAndView toursModel = new ModelAndView();
        try {
            LocalDate addStartDate = Validator.getDate(startDate, true);
            LocalDate addEndDate = Validator.getDate(endDate, true);
            toursModel.addObject("hotels", hotelService.getMapOfHotels());
            int generalAmount = toursOfferService.amountOfToursSearched(country,addStartDate,addEndDate);
            int amount = toursOfferService.totalAmountOfRows;
            List<TourOffer> tours = toursOfferService.searchTours(country, addStartDate, addEndDate,id,amount);
            toursModel.addObject("list", tours);
            toursModel.addObject("generalAmount", generalAmount);
            toursModel.addObject("amount", (generalAmount % amount == 0) ? generalAmount / amount : generalAmount / amount + 1);
            toursModel.addObject("country", country);
            toursModel.addObject("addStartDate", addStartDate);
            toursModel.addObject("addEndDate", addEndDate);
            toursModel.addObject("numberOfPeople", numberOfPeople);
            toursModel.setViewName("homepage");
            return toursModel;
        } catch (Exception e) {
            toursModel.addObject("error", e.getMessage());
            toursModel.setViewName("homepage");
            return toursModel;
        }
    }

    @GetMapping("/deletetour/{id}")
    public ModelAndView deleteTour(@PathVariable Integer id, RedirectAttributes redir) {
        ModelAndView toursModel = new ModelAndView();
        toursModel.setViewName("redirect:/listoftours");
        try {
            if (toursOfferService.deleteTour(id) == 1) {
                redir.addFlashAttribute("result","Success");
            } else {
                redir.addFlashAttribute("error","Failed to delete");
            }
            return toursModel;
        } catch (Exception e){
            redir.addFlashAttribute("error",e.getMessage());
            return toursModel;
        }
    }

    @PostMapping("/addtour")
    public ModelAndView addTour(@RequestParam String tourType, @RequestParam String startDate, @RequestParam String endDate,
                                @RequestParam String hotel, @RequestParam String pricePerPerson, @RequestParam String discount,
                                @RequestParam String tourDescription) {
        ModelAndView toursModel = new ModelAndView();
        List<Hotel> hotels = hotelService.getHotels();
        toursModel.addObject("hotelList", hotels );
        try {
            LocalDate addStartDate = Validator.getDate(startDate, false);
            LocalDate addEndDate = Validator.getDate(endDate, false);
            Integer addPricePerPerson = Validator.getInt(pricePerPerson);
            Validator.checkEmpty(tourType);
            Validator.checkEmpty(tourDescription);
            Validator.dateDifference(addStartDate,addEndDate);
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
            log.error(e.getMessage());
            toursModel.addObject("tourType", tourType);
            toursModel.addObject("startDate", startDate);
            toursModel.addObject("endDate", endDate);
            toursModel.addObject("price", pricePerPerson);
            toursModel.addObject("discount", discount);
            toursModel.addObject("description", tourDescription);
            return toursModel;
        }
    }

    @PostMapping("/updatetour")
    public ModelAndView updateTour(@RequestParam String tourId, @RequestParam String tourType,
                                   @RequestParam String pricePerPerson, @RequestParam String discount, @RequestParam String tourDescription) {
        ModelAndView toursModel = new ModelAndView();
        try {
            Integer addTourId = Validator.getInt(tourId);
            TourOffer tourOffer = toursOfferService.getTourById(addTourId);
            toursModel.addObject("tour",tourOffer);
            Integer addPricePerPerson = Validator.getInt(pricePerPerson);
            Integer addDiscount = Validator.getInt(discount);
            Validator.checkEmpty(tourType);
            Validator.checkEmpty(tourDescription);
            toursOfferService.updateTour(tourOffer,tourType,addPricePerPerson,addDiscount,tourDescription);
            toursModel.setViewName("redirect:/listoftours");
            return toursModel;
        } catch (Exception e) {
            log.error(e.getMessage());
            toursModel.addObject("error", e.getMessage());
            return toursModel;
        }
    }

    @GetMapping("/addtour")
    public String addTour(ModelMap modelMap) {
        List<Hotel> hotels = hotelService.getHotels();
        modelMap.addAttribute("hotelList", hotels);
        return "addtour";
    }

    @GetMapping("/updatetour/{id}")
    public ModelAndView updateTour(@PathVariable Integer id) {
        ModelAndView toursModel = new ModelAndView();
        toursModel.setViewName("updatetour");
        toursModel.addObject("tour",toursOfferService.getTourById(id));
        return toursModel;
    }

    @PostMapping("/reserveTour")
    public ModelAndView addReservation(@RequestParam(name = "idOfTour") Integer idOfTour,
                                       @RequestParam(name = "pricePerUnit") Integer pricePerUnit,
                                       @RequestParam(name = "numberOfPeople") Integer numberOfPeople,
                                       @RequestParam(name = "discountId") Integer discountId,
                                       Principal principal, ModelAndView modelAndView) {
        return reservationService.reserveTour(modelAndView,principal,idOfTour,pricePerUnit,numberOfPeople,discountId);
    }
}
