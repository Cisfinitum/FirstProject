package com.epam.controller;

import com.epam.model.TourOffer;
import com.epam.service.TourOfferService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.time.LocalDate;
import java.time.DateTimeException;

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
        toursModel.setViewName("homepage");
        return toursModel;
    }

    @PostMapping("/searchtours")
    public ModelAndView searchTours() {
        ModelAndView toursModel = new ModelAndView();
        toursModel.addObject("list",toursOfferService.searchTours("Turkey",LocalDate.of(2019, 5, 23), LocalDate.of(2019,5,26)));
        toursModel.setViewName("homepage");
        return toursModel;
    }

    @PostMapping("/deletetour")
    public ModelAndView deleteTour(@RequestParam String idOfTour) {
        ModelAndView toursModel = new ModelAndView();
        toursModel.addObject("result",toursOfferService.deleteTour(110));
        toursModel.setViewName("homepage");
        return toursModel;
    }

    @PostMapping("/addtour")
    @SneakyThrows(Exception.class)
    public ModelAndView addTour(@RequestParam String tourType, @RequestParam String startDate, @RequestParam String endDate,
                                @RequestParam String country, @RequestParam String city, @RequestParam String hotel,
                                @RequestParam String pricePerPerson, @RequestParam String discount, @RequestParam String tourDescription){
        LocalDate addStartDate;
        LocalDate addEndDate;
        String[] splitedStartDate = startDate.split(" ");
        String[] splitedEndDate = endDate.split(" ");
        try {
            if (!startDate.isEmpty()) {
                addStartDate = LocalDate.of(Integer.valueOf(splitedStartDate[0]), Integer.valueOf(splitedStartDate[1]),
                        Integer.valueOf(splitedStartDate[2]));
            } else {
                throw new Exception("Nullpointer in startDate");
            }
            if (!endDate.isEmpty()) {
                addEndDate = LocalDate.of(Integer.valueOf(splitedEndDate[0]), Integer.valueOf(splitedEndDate[1]),
                        Integer.valueOf(splitedEndDate[2]));
            } else {
                throw new Exception("Nullpointer in endDate");
            }
        } catch (NumberFormatException | DateTimeException e){
            throw new Exception("Wrong input in Date");
        }

        if(tourType.isEmpty())
            throw new Exception("Nullpointer in tourType");

        try {
            if(Integer.valueOf(pricePerPerson)==0){
                throw new Exception("0 input in pricePerPerson");
            }
        } catch (NumberFormatException e){
            throw new Exception("Wrong input in pricePerPerson");
        }

        if(tourDescription.isEmpty())
            throw new Exception("Nullpointer in tourDescription");

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
        ModelAndView toursModel = new ModelAndView();
        toursModel.addObject("result", result);
        toursModel.setViewName("testadmin");
        return toursModel;
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
}
