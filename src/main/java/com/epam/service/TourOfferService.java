package com.epam.service;

import com.epam.model.TourOffer;
import com.epam.repository.TourOfferDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class TourOfferService {

    private final TourOfferDAO tourOfferDAO;

    @Autowired
    public TourOfferService(TourOfferDAO tourOfferDAO){
        this.tourOfferDAO = tourOfferDAO;
    }


    List<TourOffer> getTours(){
            return tourOfferDAO.getTours();
    }

    int deleteTour(Integer tourId){
         return tourOfferDAO.deleteTour(tourId);
    }

    int addTour(Integer tourId, String tourType, Date startDate, Date endDate, Integer pricePerUnit, Integer hotel_id, String description, Integer discount_id){
        return tourOfferDAO.addTour(TourOffer.builder()
                .id(tourId)
                .tourType(tourType)
                .startDate(startDate)
                .endDate(endDate)
                .pricePerUnit(pricePerUnit)
                .hotel_id(hotel_id)
                .description(description)
                .discount_id(discount_id)
                .build());
    }

    int updateTour(Integer tourId, String tourType, Date startDate, Date endDate, Integer pricePerUnit, Integer hotel_id, String description, Integer discount_id){
        return tourOfferDAO.updateTour(TourOffer.builder()
                .id(tourId)
                .tourType(tourType)
                .startDate(startDate)
                .endDate(endDate)
                .pricePerUnit(pricePerUnit)
                .hotel_id(hotel_id)
                .description(description)
                .discount_id(discount_id)
                .build());
    }
}
