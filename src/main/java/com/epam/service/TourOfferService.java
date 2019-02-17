package com.epam.service;

import com.epam.model.TourOffer;
import com.epam.repository.TourOfferDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
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
        if(tourId != null)
         return tourOfferDAO.deleteTour(tourId);
        else {
            throw new NullPointerException();
        }
    }

    public int addTour(TourOffer tourOffer){
        if(tourOffer != null)
        return tourOfferDAO.addTour(tourOffer);
        else {
            throw new NullPointerException();
        }
    }

    public int updateTour(TourOffer tourOffer){
        if(tourOffer !=null)
        return tourOfferDAO.updateTour(tourOffer);
        else {
            throw new NullPointerException();
        }
    }

    public List<TourOffer> searchTours(String country, LocalDate startDate, LocalDate endDate){
        /// Arg "country" will used to get listOfHotelsId by CRUD method of Hotel entity
        if(country==null&&startDate==null&&endDate==null)
            return tourOfferDAO.getTours();
        else {
            List<Integer> listOfHotelsId = new ArrayList<>();
            listOfHotelsId.add(1);
            return tourOfferDAO.searchTours(listOfHotelsId, startDate, endDate);
        }
    }
}
