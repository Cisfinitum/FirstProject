package com.epam.service;

import com.epam.exception.NotFoundException;
import com.epam.model.Hotel;
import com.epam.model.Reservation;
import com.epam.model.TourOffer;
import com.epam.repository.TourOfferDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

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
    public static final int totalAmountOfRows = 5;
    public static final int ROWS_PER_PAGE = 4;

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

    public List<TourOffer> getToursByPage(Integer pageNum){
        if ((pageNum == null) || (pageNum < 1)) {
            throw new IllegalArgumentException("Page number must be integer and > 0");
        }
        Integer from = 1;
        if(pageNum > 1) {
            from = (pageNum - 1) * ROWS_PER_PAGE + 1;
        }
            return tourOfferDAO.getToursByPage(from, ROWS_PER_PAGE);
    }

    public int deleteTour(Integer tourId){
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
        } else if(tourOffer.getTourType() == null || tourOffer.getPricePerUnit() == null || tourOffer.getHotelId() == null || tourOffer.getId() == null ||
                tourOffer.getDescription() == null || tourOffer.getDiscount() == null || tourOffer.getStartDate() == null || tourOffer.getEndDate() == null) {
            log.error("Some fields of tourOffer is empty");
            throw new IllegalArgumentException("Some fields of tourOffer is empty");
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
        } else if (addDiscount == null || addDiscount < 0) {
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
            tourOffer.setDiscount(addDiscount);
            tourOffer.setDescription(tourDescription);
            int result = tourOfferDAO.updateTour(tourOffer);
            if(result == 0) {
                log.error("Update was failed");
                throw new NotFoundException("Update was failed");
            }
            return result;
        }
    }

    public List<TourOffer> searchTours(String country, LocalDate startDate, LocalDate endDate) {
        List<Hotel> myList = hotelService.getHotelsByCountry(country);
        if (myList.size() == 0 && !country.isEmpty()) {
            return new ArrayList<TourOffer>();
        }
        if (page > 0 && totalAmountOfRows > 0) {
            if (page > 1) {
                page = (page - 1) * totalAmountOfRows + 1;
            }
            List<Integer> listOfHotelsId = new ArrayList<>();
            for(Hotel hotel: myList){
                listOfHotelsId.add(hotel.getId());
            }
            List<TourOffer> listOfTours = tourOfferDAO.searchTours(listOfHotelsId, startDate, endDate, page, totalAmountOfRows);
            if(listOfTours.size() == 0){
                return new ArrayList<TourOffer>();
            }
            return listOfTours;
        } else {
            throw new IllegalArgumentException("Numbers must be integer and > 0");
        }
    }

    public TourOffer getTourById(Integer tourId) {
        if (tourId == null || tourId == 0) {
            log.error("toursId is null or 0");
            throw new IllegalArgumentException("toursId is null or 0");
        } else {
            return tourOfferDAO.getTourById(tourId);
        }
    }

    public int checkIfHotelUsed(Integer hotelId){
        if (hotelId == null || hotelId <= 0) {
            log.error("toursId is null, negative or 0");
            throw new IllegalArgumentException("toursId is null, negative or 0");
        }else {
            return tourOfferDAO.checkIfHotelsIsUsed(hotelId);
            }
    }

    public Map<Integer, Boolean> getMapOfHotelUse(List <Hotel> hotels){
        Map<Integer, Boolean> map = new HashMap<>();
        for (Hotel hotel : hotels) {
            Integer hotelId = hotel.getId();
            Boolean isHotelUsed = checkIfHotelUsed(hotelId) == 1;
            map.put(hotelId, isHotelUsed);
        }
        return map;
    }
    public Map<Integer,String> getDescription(List<Reservation> reservations){
        Map<Integer, String> description = new HashMap<>();
        for (Reservation reservation : reservations) {
            Integer tourId = reservation.getTourOfferId();
            TourOffer tourOffer = getTourById(tourId);
            Integer hotelId = tourOffer.getHotelId();
            Hotel hotel = hotelService.getHotelById(hotelId);
            description.put(tourId, tourOffer.toString() + hotel.toString());
        }
        return description;
    }

    public int getAmountOfTours() {
        return tourOfferDAO.getAmountOfTours();
    }

    public int getNumberOfPages () {
        Integer amountOfTours = tourOfferDAO.getAmountOfTours();
        return (amountOfTours % ROWS_PER_PAGE == 0) ? amountOfTours / ROWS_PER_PAGE : amountOfTours / ROWS_PER_PAGE + 1;
    }

    public int amountOfToursSearched(String country, LocalDate startDate, LocalDate endDate) {
        List<Hotel> myList = hotelService.getHotelsByCountry(country);
        if( myList.size() == 0 && !country.isEmpty() ){
            log.error("Wrong input country: "+country);
            throw new IllegalArgumentException("Wrong input country: "+country);
        }
            List<Integer> listOfHotelsId = new ArrayList<>();
            for(Hotel hotel: myList){
                listOfHotelsId.add(hotel.getId());
            }
            return tourOfferDAO.amountOfToursSearched(listOfHotelsId, startDate, endDate);
    }

    public int getNumberOfPagesSearch (Integer generalAmount) {
        return (generalAmount % totalAmountOfRows == 0) ? generalAmount / totalAmountOfRows : generalAmount / totalAmountOfRows + 1;
    }
}
