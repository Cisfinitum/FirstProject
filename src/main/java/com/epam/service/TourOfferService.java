package com.epam.service;

import com.epam.model.TourOffer;
import com.epam.repository.TourOfferDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TourOfferService {

    private final TourOfferDAO tourOfferDAO;

    @Autowired
    public TourOfferService(TourOfferDAO tourOfferDAO){
        this.tourOfferDAO = tourOfferDAO;
    }


    public List<TourOffer> getTours(){
            return tourOfferDAO.getTours();
    }

    public int deleteTour(Integer tourId){
        if(tourId!=null&&tourId!=0)
            return tourOfferDAO.deleteTour(tourId);
        else {
            log.error("tourId is null or 0");
            throw new NullPointerException("tourId is null or 0");
        }
    }

    public int addTour(TourOffer tourOffer){
        if(tourOffer!=null)
            return tourOfferDAO.addTour(tourOffer);
        else {
            log.error("tourOffer is null");
            throw new NullPointerException("tourOffer is null");
        }
    }

    public int updateTour(TourOffer tourOffer, String tourType, Integer addPricePerPerson, Integer addDiscount, String tourDescription){
        if(tourOffer == null) {
            log.error("tourOffer is null");
            throw new IllegalArgumentException("tourOffer is null");
        }
        else if(tourType == null){
            log.error("tourType is null");
            throw new IllegalArgumentException("tourType is null");
        }
        else if(addPricePerPerson == null || addPricePerPerson <= 0){
            log.error("addPricePerPerson is null or 0");
            throw new IllegalArgumentException("addPricePerPerson is null or 0");
        }
        else if(addDiscount == null || addDiscount <= 0){
            log.error("addDiscount is null or 0");
            throw new IllegalArgumentException("addDiscount is null or 0");
        }
        else if(tourDescription == null){
            log.error("tourDescription is null");
            throw new IllegalArgumentException("tourDescription is null");
        }
        else {
            tourOffer.setTourType(tourType);
            tourOffer.setPricePerUnit(addPricePerPerson);
            tourOffer.setDiscountId(addDiscount);
            tourOffer.setDescription(tourDescription);
            return tourOfferDAO.updateTour(tourOffer);
        }
    }

    public List<TourOffer> searchTours(String country, LocalDate startDate, LocalDate endDate){
        /// Arg "country" will used to get listOfHotelsId by CRUD method of Hotel entity
            List<Integer> listOfHotelsId = new ArrayList<>();
            listOfHotelsId.add(1);
            return tourOfferDAO.searchTours(listOfHotelsId, startDate, endDate);
    }

    public TourOffer getTourById(Integer tourId){
        if( tourId == null || tourId == 0) {
            log.error("toursId is null or 0");
            throw new IllegalArgumentException("toursId is null or 0");
        } else {
            return tourOfferDAO.getTourById(tourId);
        }
    }
}
