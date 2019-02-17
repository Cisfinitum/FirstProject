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


    public List<TourOffer> getTours(){
            return tourOfferDAO.getTours();
    }

    public int deleteTour(int tourId){
         return tourOfferDAO.deleteTour(tourId);
    }

    public int addTour(int tourId, String tourType, Date startDate, Date endDate, int pricePerUnit, int hotel_id, String description, int discount_id){
        TourOffer touroffer = TourOffer.builder()
                .id(tourId)
                .tourType(tourType)
                .startDate(startDate)
                .endDate(endDate)
                .pricePerUnit(pricePerUnit)
                .hotel_id(hotel_id)
                .description(description)
                .discount_id(discount_id)
                .build();
        return tourOfferDAO.addTour(touroffer);
    }

    public int updateTour(int tourId, String tourType, Date startDate, Date endDate, int pricePerUnit, int hotel_id, String description, int discount_id){
        TourOffer touroffer = TourOffer.builder()
                .id(tourId)
                .tourType(tourType)
                .startDate(startDate)
                .endDate(endDate)
                .pricePerUnit(pricePerUnit)
                .hotel_id(hotel_id)
                .description(description)
                .discount_id(discount_id)
                .build();
        return tourOfferDAO.updateTour(touroffer);
    }
}
