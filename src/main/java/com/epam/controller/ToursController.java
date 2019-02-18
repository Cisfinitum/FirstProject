package com.epam.controller;

import com.epam.model.TourOffer;
import com.epam.service.TourOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.time.LocalDate;
import java.time.DateTimeException;

import static java.time.temporal.ChronoUnit.DAYS;

@Controller
public class ToursController {
    private final TourOfferService toursOfferService;
    @Autowired
    ToursController(TourOfferService toursOfferService){
        this.toursOfferService = toursOfferService;
    }

    @GetMapping("/listoftours")
    public ModelAndView getToursList() {
        ModelAndView toursModel = new ModelAndView();
        toursModel.addObject("list",toursOfferService.getTours());
        toursModel.setViewName("tours");
        return toursModel;
    }

    @PostMapping("/searchtours")
    public ModelAndView searchTours() {
        ModelAndView toursModel = new ModelAndView();
        toursModel.addObject("list",toursOfferService.searchTours(null,null, null));
        toursModel.setViewName("homepage");
        return toursModel;
    }

    @PostMapping("/deletetour")
    public ModelAndView deleteTour(@RequestParam String idOfTour) {
        //Need to check here reservations which contain current tour id or we ll have exception
        ModelAndView toursModel = new ModelAndView();
        toursModel.addObject("result",toursOfferService.deleteTour(Integer.valueOf(idOfTour))==1?"Success":"Failed to delete");
        toursModel.setViewName("redirect:/listoftours");
        return toursModel;
    }

    @PostMapping("/addtour")
    public ModelAndView addTour(@RequestParam String tourType, @RequestParam String startDate, @RequestParam String endDate,
                                @RequestParam String country, @RequestParam String city, @RequestParam String hotel,
                                @RequestParam String pricePerPerson, @RequestParam String discount, @RequestParam String tourDescription){
        LocalDate addStartDate;
        LocalDate addEndDate;
        String[] splitedStartDate = startDate.split(" ");
        String[] splitedEndDate = endDate.split(" ");
        ModelAndView toursModel = new ModelAndView();
        try {
            try {
                if (!startDate.isEmpty()) {
                    addStartDate = LocalDate.of(Integer.valueOf(splitedStartDate[0]), Integer.valueOf(splitedStartDate[1]),
                            Integer.valueOf(splitedStartDate[2]));
                } else {
                    throw new NullPointerException("Day of department is empty");
                }
                if (!endDate.isEmpty()) {
                    addEndDate = LocalDate.of(Integer.valueOf(splitedEndDate[0]), Integer.valueOf(splitedEndDate[1]),
                            Integer.valueOf(splitedEndDate[2]));
                } else {
                    throw new NullPointerException("Arrive date is empty");
                }
            } catch (NumberFormatException | DateTimeException e) {
                throw new Exception("One of the Date incorrect");
            }

            if(!(DAYS.between(addStartDate,addEndDate)>=1))
                throw new Exception("Negative difference between Dates");

            if (tourType.isEmpty()) {
                throw new NullPointerException("Tour type is empty");
            }

            try {
                if (Integer.valueOf(pricePerPerson) < 0) {
                    throw new Exception("Price per person negative or 0");
                }
            } catch (NumberFormatException e) {
                throw new Exception("Price per person incorrect");
            }

            if (tourDescription.isEmpty())
                throw new NullPointerException("Tour description is empty");

            //Here we ll use method from HotelService to create new row in DB for Hotel entity, after that we rather to pull hotel_id
            //The same actions we ll do for discount_id

            Integer result = toursOfferService.addTour(TourOffer.builder()
                    .id(1)
                    .tourType(tourType)
                    .startDate(addStartDate)
                    .endDate(addEndDate)
                    .pricePerUnit(200)
                    .hotelId(1) //stub
                    .description(tourDescription)
                    .discountId(1) //stub
                    .build());

            toursModel.addObject("result",result==1?"Success":"Failed to add");
            toursModel.setViewName("addtour");
            return toursModel;

        } catch (Exception e){
            toursModel.addObject("result",e.getMessage());
            return toursModel;
        }
    }

    @PostMapping("/updatetour")
    public ModelAndView updateTour() {
        Integer result = toursOfferService.updateTour(TourOffer.builder()
                .id(1)
                .tourType("test")
                .startDate(LocalDate.of(2025,10,10))
                .endDate(LocalDate.of(2025,10,18))
                .pricePerUnit(200)
                .hotelId(1)
                .description("test")
                .discountId(1)
                .build());
        ModelAndView toursModel = new ModelAndView();
        toursModel.addObject("result", result);
        toursModel.setViewName("homepage");
        return toursModel;
    }

    @GetMapping("/addtour")
    public String getAddTour(){
        return "addtour";
    }
}
