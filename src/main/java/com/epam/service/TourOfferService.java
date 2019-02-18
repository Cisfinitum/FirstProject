package com.epam.service;

import com.epam.model.TourOffer;
import com.epam.repository.TourOfferDAO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

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
        if(tourId != null)
         return tourOfferDAO.deleteTour(tourId);
        else {
            throw new NullPointerException();
        }
    }

    @SneakyThrows
    public int addTour(TourOffer tourOffer){
        if (tourOffer.getDescription().isEmpty()) {
            log.error("Tour description is empty. User current tour description: " + tourOffer.getDescription());
            throw new NullPointerException("Tour description is empty. User current tour description: " + tourOffer.getDescription());
        }
        if (tourOffer.getTourType().isEmpty()) {
            log.error("Tour type is empty. User current input tour type: "+tourOffer.getTourType());
            throw new NullPointerException("Tour type is empty. User current input tour type: "+tourOffer.getTourType());
        }
        if(!(DAYS.between(tourOffer.getStartDate(),tourOffer.getEndDate())>=1)) {
            log.error("Negative difference between dates. Current difference: " + DAYS.between(tourOffer.getStartDate(),tourOffer.getEndDate()));
            throw new Exception("Negative difference between Dates");
        }
        return tourOfferDAO.addTour(tourOffer);
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
