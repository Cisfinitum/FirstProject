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

    /// TODO replace (int hotelId) on (List<Integer> listHotels)
    public List<TourOffer> getTours(int hotelId, Date startDate, int numberOfDays, int numberOfPeople){
 //       List<Person> persons = personDAO.getPersons();
 //       for (Person person: persons) {
 //           if (person.getName().equals(name)) return person;
 //       }
        return null;
    }

    public int deleteTour(int tourId){
         return tourOfferDAO.deleteTour(tourId);
    }

    public int addTour(String tourType, Date startDate, Date endDate, int pricePerUnit, int hotel_id, String description, int discount_id){
        return tourOfferDAO.addTour(tourType,startDate,endDate,pricePerUnit,hotel_id,description,discount_id);
    }

    public int updateTour(String tourType, Date startDate, Date endDate, int pricePerUnit, int hotel_id, String description, int discount_id, int tourId){
        return tourOfferDAO.updateTour(tourType,startDate,endDate,pricePerUnit,hotel_id,description,discount_id,tourId);
    }
}
