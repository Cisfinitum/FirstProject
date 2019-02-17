package com.epam.controller;

import com.epam.service.TourOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import java.sql.Date;

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
        toursModel.addObject("list",toursOfferService.searchTours("Turkey",Date.valueOf("2019-05-23"),Date.valueOf("2019-05-26")));
        toursModel.setViewName("homepage");
        return toursModel;
    }

    @GetMapping("/deletetour")
    public ModelAndView deleteTour() {
        ModelAndView toursModel = new ModelAndView();
        toursModel.addObject("result",toursOfferService.deleteTour(110));
        toursModel.setViewName("homepage");
        return toursModel;
    }

    @PostMapping("/addtour")
    public ModelAndView addTour() {
        ModelAndView toursModel = new ModelAndView();
        toursModel.addObject("result", toursOfferService.addTour(110,"test",
                Date.valueOf("2025-10-10"),Date.valueOf("2025-10-25"),200,1,"test",1));
        toursModel.setViewName("homepage");
        return toursModel;
    }

    @PostMapping("/updatetour")
    public ModelAndView updateTour() {
        ModelAndView toursModel = new ModelAndView();
        toursModel.addObject("result", toursOfferService.updateTour(110,"test",
                Date.valueOf("2025-10-10"),Date.valueOf("2025-10-25"),200,1,"test",1));
        toursModel.setViewName("homepage");
        return toursModel;
    }
}
