package com.epam.controller;

import com.epam.model.Hotel;
import com.epam.model.TourOffer;
import com.epam.service.HotelService;
import com.epam.service.PersonService;
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
import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.epam.service.TourOfferService.ROWS_PER_PAGE;


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
                                    @RequestParam String endDate, @RequestParam String numberOfPeople) {
        ModelAndView toursModel = new ModelAndView();
        toursModel.setViewName("homepage");
        LocalDate addStartDate = null;
        LocalDate addEndDate = null;
        try {
            if(!startDate.isEmpty()) {
                addStartDate = Date.valueOf(startDate).toLocalDate();
            }
            if(!endDate.isEmpty()) {
                addEndDate = Date.valueOf(endDate).toLocalDate();
            }
        } catch (NumberFormatException | DateTimeException e) {
            return toursModel.addObject("error","Wrong format of Date");
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
        toursModel.addObject("hotelList", hotels );
        toursModel.addObject("tourType", tourType);
        toursModel.addObject("startDate", startDate);
        toursModel.addObject("endDate", endDate);
        toursModel.addObject("price", pricePerPerson);
        toursModel.addObject("discount", discount);
        toursModel.addObject("description", tourDescription);
        toursModel.setViewName("addtour");
        LocalDate addStartDate;
        LocalDate addEndDate;
        try {
            if(!startDate.isEmpty()) {
                addStartDate = Date.valueOf(startDate).toLocalDate();
            } else {
                return toursModel.addObject("error","Empty Date");
            }
            if(!endDate.isEmpty()) {
                addEndDate = Date.valueOf(endDate).toLocalDate();
            } else {
                return toursModel.addObject("error","Empty Date");
            }
        } catch (NumberFormatException | DateTimeException e) {
            return toursModel.addObject("error","Wrong format of Date");
        }
        Pattern pricePattern = Pattern.compile("[0-9]+");
        Matcher priceMatcher = pricePattern.matcher(pricePerPerson);
        Pattern discountPatten = Pattern.compile("[0-9]{1,3}");
        Matcher discountMatcher = discountPatten.matcher(discount);
        if (!priceMatcher.matches()) {
            return toursModel.addObject("error", "Invalid price");
        } else if (!discountMatcher.matches()||Integer.valueOf(discount)>100) {
            return toursModel.addObject("error", "Discount may be from 0 to 100");
        } else if(tourType.isEmpty()){
            return toursModel.addObject("error", "TourType empty");
        } else if(tourDescription.isEmpty()){
            return toursModel.addObject("error", "TourDescription empty");
        } else {
            int result = toursOfferService.addTour(TourOffer.builder()
                    .id(1)
                    .tourType(tourType)
                    .startDate(addStartDate)
                    .endDate(addEndDate)
                    .pricePerUnit(Integer.valueOf(pricePerPerson))
                    .hotelId(1) //stub
                    .description(tourDescription)
                    .discount(Integer.valueOf(discount))
                    .build());
            if (result == 1) {
                toursModel.addObject("result", "Success");
            } else {
                toursModel.addObject("error", "Failed to add");
            }
            return toursModel;
        }
    }

    @PostMapping("/updatetour")
    public ModelAndView updateTour(@RequestParam String tourId, @RequestParam String tourType,
                                   @RequestParam String pricePerPerson, @RequestParam String discount, @RequestParam String tourDescription) {
        ModelAndView toursModel = new ModelAndView();
        TourOffer tourOffer = toursOfferService.getTourById(Integer.valueOf(tourId));
        toursModel.addObject("tour",tourOffer);
        Pattern pricePattern = Pattern.compile("[0-9]+");
        Matcher priceMatcher = pricePattern.matcher(pricePerPerson);
        Pattern discountPatten = Pattern.compile("[0-9]{1,3}");
        Matcher discountMatcher = discountPatten.matcher(discount);
        if (!priceMatcher.matches()) {
            return toursModel.addObject("error", "Invalid price");
        } else if (!discountMatcher.matches()||Integer.valueOf(discount)>100) {
            return toursModel.addObject("error", "Discount may be from 0 to 100");
        } else if(tourType.isEmpty()){
            return toursModel.addObject("error", "TourType empty");
        } else if(tourDescription.isEmpty()){
            return toursModel.addObject("error", "TourDescription empty");
        } else {
            toursOfferService.updateTour(tourOffer, tourType, Integer.valueOf(pricePerPerson), Integer.valueOf(tourDescription), tourDescription);
            toursModel.setViewName("redirect:/listoftours");
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
