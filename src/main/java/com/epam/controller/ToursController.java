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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
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

    @GetMapping("/listoftours/{pageNum}")
    public ModelAndView getToursListByPage(@PathVariable Integer pageNum, ModelMap modelMap) {
        ModelAndView toursModel = new ModelAndView();
        Integer from = 0;
        Integer rowsPerPage = toursOfferService.rowsPerPage;
        Integer totalRows = toursOfferService.getAmountOfTours();
        Integer totalPages = (totalRows % rowsPerPage == 0) ? totalRows / rowsPerPage : totalRows / rowsPerPage + 1;
        if(pageNum != 1) {
            from = (pageNum - 1) * rowsPerPage + 1;
        }
        toursModel.addObject("list", toursOfferService.getToursByPage(from, rowsPerPage));
        toursModel.setViewName("tours");
        modelMap.addAttribute("rowsPerPage", rowsPerPage);
        modelMap.addAttribute("totalRows", totalRows);
        modelMap.addAttribute("totalPages", totalPages);
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
            toursModel.addObject("hotels", hotelService.getMapOfHotels());
            toursModel.addObject("list", toursOfferService.searchTours(country, addStartDate, addEndDate));
            return toursModel;
        } catch (Exception e) {
            toursModel.addObject("error", e.getMessage());
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
            int result = toursOfferService.updateTour(tourOffer,tourType,addPricePerPerson,addDiscount,tourDescription);
            if (result == 1) {
                toursModel.setViewName("redirect:/listoftours");
            } else {
                toursModel.addObject("error", "Failed to add");
            }
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
