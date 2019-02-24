package com.epam.service;

import com.epam.model.Hotel;
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
    private final HotelService hotelService;

    @Autowired
    public TourOfferService(TourOfferDAO tourOfferDAO, HotelService hotelService){
        this.tourOfferDAO = tourOfferDAO;
        this.hotelService = hotelService;
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

    public int updateTour(TourOffer tourOffer){
        if(tourOffer!=null)
            return tourOfferDAO.updateTour(tourOffer);
        else {
            log.error("tourOffer is null");
            throw new NullPointerException("tourOffer is null");
        }
    }

    public List<TourOffer> searchTours(String country, LocalDate startDate, LocalDate endDate){
        List<Hotel> myList = hotelService.getHotelsByCountry(country);
        if(myList.size()==0&&!country.isEmpty()){
            log.error("Wrong input country: "+country);
            throw new IllegalArgumentException("Wrong input country: "+country);
        }
        List<Integer> listOfHotelsId = new ArrayList<>();
        for(Hotel hotel: myList){
            listOfHotelsId.add(hotel.getId());
        }
            return tourOfferDAO.searchTours(listOfHotelsId, startDate, endDate);
    }
}
