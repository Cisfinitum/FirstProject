package com.epam.controller;

import com.epam.validator.Validator;
import com.epam.model.Hotel;
import com.epam.model.TourOffer;
import com.epam.service.HotelService;
import com.epam.service.ReservationService;
import com.epam.service.TourOfferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import static com.epam.service.TourOfferService.ROWS_PER_PAGE;


@Controller
@Slf4j
public class ToursController {
    private final TourOfferService toursOfferService;
    private final HotelService hotelService;
    private final ReservationService reservationService;

    @Autowired
    public ToursController(TourOfferService toursOfferService, ReservationService reservationService, HotelService hotelService) {
        this.toursOfferService = toursOfferService;
        this.hotelService = hotelService;
        this.reservationService = reservationService;
    }

    @GetMapping("/listoftours")
    public String testadmin(RedirectAttributes redirectAttributes) {
        return "redirect:/listoftours/1";
    }

    @GetMapping("/listoftours/{pageNum}")
    public ModelAndView getToursListByPage(@PathVariable Integer pageNum, ModelMap modelMap, RedirectAttributes redirectAttributes) {
        ModelAndView toursModel = new ModelAndView();
        Integer totalRows = toursOfferService.getAmountOfTours();
        Integer totalPages = toursOfferService.getNumberOfPages();
        modelMap.addAttribute("rowsPerPage", ROWS_PER_PAGE);
        modelMap.addAttribute("totalPages", totalPages);
        toursModel.addObject("listOfTours", toursOfferService.getToursByPage(pageNum));
        toursModel.addObject("hotels", hotelService.getMapOfHotels());
        toursModel.addObject("isReservedMap", toursOfferService.getToursStatusMap());
        if (redirectAttributes != null) {
            for (String string: redirectAttributes.getFlashAttributes().keySet())
                toursModel.addObject(string, redirectAttributes.getFlashAttributes().get(string));
        }
        toursModel.setViewName("tours");
        return toursModel;
    }

    @PostMapping("/searchtours")
    public ModelAndView searchTours(@RequestParam String country, @RequestParam String startDate,
                                    @RequestParam String endDate) {
        ModelAndView toursModel = new ModelAndView();
        toursModel.setViewName("homepage");
        LocalDate addStartDate;
        LocalDate addEndDate;
        try {
            addStartDate = Validator.getDateFromString(startDate,true);
            addEndDate = Validator.getDateFromString(endDate,true);
        } catch (IllegalArgumentException e) {
            return toursModel.addObject("error",e.getMessage());
        }
        toursModel.addObject("hotels", hotelService.getMapOfHotels());
        List<TourOffer> searchedListOfTours = toursOfferService.searchTours(country, addStartDate, addEndDate);
        if(searchedListOfTours.size() == 0) {
            return toursModel.addObject("error","Search result is empty");
        } else {
            return toursModel.addObject("list",searchedListOfTours);
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
        toursModel.addObject("hotelList", hotels);
        toursModel.addObject("tourType", tourType);
        toursModel.addObject("startDate", startDate);
        toursModel.addObject("endDate", endDate);
        toursModel.addObject("price", pricePerPerson);
        toursModel.addObject("discount", discount);
        toursModel.addObject("description", tourDescription);
        toursModel.setViewName("addtour");
        TourOffer addTourOffer;
        try {
            addTourOffer = TourOffer.builder()
                    .id(1)
                    .tourType(Validator.getTourTypeString(tourType))
                    .startDate(Validator.getDateFromString(startDate,false))
                    .endDate(Validator.getDateFromString(endDate,false))
                    .pricePerUnit(Validator.getPriceFromString(pricePerPerson))
                    .hotelId(1) //stub
                    .description(Validator.getDescriptionString(tourDescription))
                    .discount(Validator.getDiscountFromString(discount))
                    .build();
        } catch (IllegalArgumentException e) {
            return toursModel.addObject("error",e.getMessage());
        }

        if (toursOfferService.addTour(addTourOffer) == 1) {
                    return toursModel.addObject("result", "Success");
        } else {
                    return toursModel.addObject("error", "Failed to add");
                }
    }

    @PostMapping("/updatetour")
    public ModelAndView updateTour(@RequestParam String tourId, @RequestParam String tourType,
                                   @RequestParam String pricePerPerson, @RequestParam String discount, @RequestParam String tourDescription) {
        ModelAndView toursModel = new ModelAndView();
        TourOffer tourOffer = toursOfferService.getTourById(Integer.valueOf(tourId));
        toursModel.addObject("tour",tourOffer);
        try{
            toursOfferService.updateTour(tourOffer, Validator.getTourTypeString(tourType), Validator.getPriceFromString(pricePerPerson),
                    Validator.getDiscountFromString(discount), Validator.getDescriptionString(tourDescription));
        } catch (IllegalArgumentException e) {
            return toursModel.addObject("error",e.getMessage());
        }
        toursModel.setViewName("redirect:/listoftours");
        return toursModel;

    }

    @GetMapping("/addtour")
    public String addTour(ModelMap modelMap) {
        List<Hotel> hotels = hotelService.getHotels();
        modelMap.addAttribute("hotelList", hotels);
        return "addtour";
    }

    @GetMapping("/updatetour/{id}")
    public ModelAndView updateTour(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        ModelAndView toursModel = new ModelAndView();
        redirectAttributes.addFlashAttribute("error", "You cannot update this tour, it's already reserved!");
        if (reservationService.getTourOfferById(id) == 1) {
            toursModel.setViewName("redirect:/listoftours");
        } else {
            toursModel.setViewName("updatetour");
            toursModel.addObject("tour",toursOfferService.getTourById(id));
        }
        return toursModel;
    }

    @PostMapping("/reserveTour")
    public ModelAndView addReservation(@RequestParam(name = "idOfTour") Integer idOfTour,
                                       @RequestParam(name = "pricePerUnit") Integer pricePerUnit,
                                       @RequestParam(name = "numberOfPeople") Integer numberOfPeople,
                                       @RequestParam(name = "discount") Integer discount,
                                       Principal principal, ModelAndView modelAndView, RedirectAttributes redirectAttributes) {
        return reservationService.reserveTour(modelAndView,principal,idOfTour,pricePerUnit,numberOfPeople,discount, redirectAttributes);
    }
}
