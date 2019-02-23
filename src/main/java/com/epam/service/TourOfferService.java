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
        if (tourId == null || tourId == 0) {
            log.error("tourId is null or 0");
            throw new IllegalArgumentException("tourId is null or 0");
        } else {
            return tourOfferDAO.deleteTour(tourId);
        }
    }

    public int addTour(TourOffer tourOffer){
        if (tourOffer == null) {
            log.error("tourOffer is null");
            throw new IllegalArgumentException("tourOffer is null");
        } else {
            return tourOfferDAO.addTour(tourOffer);
        }
    }

    public int updateTour(TourOffer tourOffer){
        if (tourOffer == null) {
            log.error("tourOffer is null");
            throw new IllegalArgumentException("tourOffer is null");
        } else {
            return tourOfferDAO.updateTour(tourOffer);
        }
    }

    public List<TourOffer> searchTours(String country, LocalDate startDate, LocalDate endDate){
        /// Arg "country" will used to get listOfHotelsId by CRUD method of Hotel entity
            List<Integer> listOfHotelsId = new ArrayList<>();
            listOfHotelsId.add(1);
            return tourOfferDAO.searchTours(listOfHotelsId, startDate, endDate);
    }
}
