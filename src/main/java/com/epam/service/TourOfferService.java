package com.epam.service;

import com.epam.model.Hotel;
import com.epam.model.TourOffer;
import com.epam.repository.TourOfferDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class TourOfferService {

    private final TourOfferDAO tourOfferDAO;
    private final HotelService hotelService;
    private final ReservationService reservationService;

    @Autowired
    public TourOfferService(TourOfferDAO tourOfferDAO, HotelService hotelService, ReservationService reservationService) {
        this.tourOfferDAO = tourOfferDAO;
        this.hotelService = hotelService;
        this.reservationService = reservationService;
    }


    public List<TourOffer> getTours() {
        return tourOfferDAO.getTours();
    }

    public Map<Integer, Boolean> getToursStatusMap() {
        List<TourOffer> listOfTours = getTours();
        Map<Integer, Boolean> toursStatusMap = new HashMap<>();
        for (TourOffer tourOffer : listOfTours) {
            Integer id = tourOffer.getId();
            Boolean tourStatus = reservationService.getTourOfferById(id) == 1 ? false : true;
            toursStatusMap.put(id, tourStatus);
        }
        return toursStatusMap;
    }

    public int deleteTour(Integer tourId) {
        if (tourId == null || tourId == 0) {
            log.error("tourId is null or 0");
            throw new IllegalArgumentException("tourId is null or 0");
        } else if (reservationService.getTourOfferById(tourId) == 1) {
            log.error("Reservation with this TourId is already exist");
            throw new IllegalArgumentException("Reservation with this TourId is already exist");
        } else {
            return tourOfferDAO.deleteTour(tourId);
        }
    }

    public int addTour(TourOffer tourOffer) {
        if (tourOffer == null) {
            log.error("tourOffer is null");
            throw new IllegalArgumentException("tourOffer is null");
        } else {
            return tourOfferDAO.addTour(tourOffer);
        }
    }

    public int updateTour(TourOffer tourOffer, String tourType, Integer addPricePerPerson, Integer addDiscount, String tourDescription) {
        if (tourOffer == null) {
            log.error("tourOffer is null");
            throw new IllegalArgumentException("tourOffer is null");
        } else if (tourType == null) {
            log.error("tourType is null");
            throw new IllegalArgumentException("tourType is null");
        } else if (addPricePerPerson == null || addPricePerPerson <= 0) {
            log.error("addPricePerPerson is null or 0");
            throw new IllegalArgumentException("addPricePerPerson is null or 0");
        } else if (addDiscount == null || addDiscount <= 0) {
            log.error("addDiscount is null or 0");
            throw new IllegalArgumentException("addDiscount is null or 0");
        } else if (tourDescription == null) {
            log.error("tourDescription is null");
            throw new IllegalArgumentException("tourDescription is null");
        } else if (reservationService.getTourOfferById(tourOffer.getId()) == 1) {
            log.error("Reservation with this TourId is already exist");
            throw new IllegalArgumentException("Reservation with this TourId is already exist");
        } else {
            tourOffer.setTourType(tourType);
            tourOffer.setPricePerUnit(addPricePerPerson);
            tourOffer.setDiscountId(addDiscount);
            tourOffer.setDescription(tourDescription);
            return tourOfferDAO.updateTour(tourOffer);
        }
    }

    public List<TourOffer> searchTours(String country, LocalDate startDate, LocalDate endDate) {
        List<Hotel> myList = hotelService.getHotelsByCountry(country);
        if (myList.size() == 0 && !country.isEmpty()) {
            log.error("Wrong input country: " + country);
            throw new IllegalArgumentException("Wrong input country: " + country);
        }
        List<Integer> listOfHotelsId = new ArrayList<>();
        for (Hotel hotel : myList) {
            listOfHotelsId.add(hotel.getId());
        }
        return tourOfferDAO.searchTours(listOfHotelsId, startDate, endDate);
    }

    public TourOffer getTourById(Integer tourId) {
        if (tourId == null || tourId == 0) {
            log.error("toursId is null or 0");
            throw new IllegalArgumentException("toursId is null or 0");
        } else {
            return tourOfferDAO.getTourById(tourId);
        }
    }
}
