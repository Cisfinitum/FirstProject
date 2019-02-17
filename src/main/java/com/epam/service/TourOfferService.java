package com.epam.service;

import com.epam.model.TourOffer;
import com.epam.repository.TourOfferDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
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
         return tourOfferDAO.deleteTour(tourId);
    }

    public int addTour(Integer tourId, String tourType, Date startDate, Date endDate, Integer pricePerUnit, Integer hotel_id, String description, Integer discount_id){
        return tourOfferDAO.addTour(TourOffer.builder()
                .id(tourId)
                .tourType(tourType)
                .startDate(startDate)
                .endDate(endDate)
                .pricePerUnit(pricePerUnit)
                .hotelId(hotel_id)
                .description(description)
                .discountId(discount_id)
                .build());
    }

    public int updateTour(Integer tourId, String tourType, Date startDate, Date endDate, Integer pricePerUnit, Integer hotel_id, String description, Integer discount_id){
        return tourOfferDAO.updateTour(TourOffer.builder()
                .id(tourId)
                .tourType(tourType)
                .startDate(startDate)
                .endDate(endDate)
                .pricePerUnit(pricePerUnit)
                .hotelId(hotel_id)
                .description(description)
                .discountId(discount_id)
                .build());
    }

    public List<TourOffer> searchTours(String country, Date startDate, Date endDate){
        /// Arg "country" will used to get listOfHotelsId by CRUD method of Hotel entity
        List<Integer> listOfHotelsId = new ArrayList<>();
        listOfHotelsId.add(1);
        return tourOfferDAO.searchTours(listOfHotelsId,startDate,endDate);
    }
}
