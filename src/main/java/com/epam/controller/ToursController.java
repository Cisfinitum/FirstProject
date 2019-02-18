package com.epam.controller;

import com.epam.model.TourOffer;
import com.epam.service.TourOfferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.time.LocalDate;
import java.time.DateTimeException;


@Controller
@Slf4j
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
                    log.error("Day of department is empty. User current input date: "+startDate);
                    throw new NullPointerException("Day of department is empty. Your current input date: "+startDate);
                }
                if (!endDate.isEmpty()) {
                    addEndDate = LocalDate.of(Integer.valueOf(splitedEndDate[0]), Integer.valueOf(splitedEndDate[1]),
                            Integer.valueOf(splitedEndDate[2]));
                } else {
                    log.error("Day of department is empty. User current input date: "+startDate);
                    throw new NullPointerException("Arrive date is empty. Your current input date: "+endDate);
                }
            } catch (NumberFormatException | DateTimeException e) {
                log.error("Date or Number format exception. User current first date: "+splitedStartDate[0]+" "+
                        splitedStartDate[1]+" "+splitedStartDate[2]+". User current second date: "+splitedEndDate[0]+" "+
                        splitedEndDate[1]+" "+splitedEndDate[2]+".");
                throw new Exception("Date or Number format exception. User current first date: "+splitedStartDate[0]+" "+
                        splitedStartDate[1]+" "+splitedStartDate[2]+". User current second date: "+splitedEndDate[0]+" "+
                        splitedEndDate[1]+" "+splitedEndDate[2]+".");
            }
            try {
                if (Integer.valueOf(pricePerPerson) < 0) {
                    log.error("Price per person negative or 0. User current price per person: "+pricePerPerson);
                    throw new Exception("Price per person negative or 0. User current price per person: "+pricePerPerson);
                }
            } catch (NumberFormatException e) {
                log.error("Price per person format exception. User current price per person: "+pricePerPerson);
                throw new Exception("Price per person format exception. User current price per person: "+pricePerPerson);
            }
            int result = toursOfferService.addTour(TourOffer.builder()
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
