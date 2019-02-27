package com.epam.service;

import com.epam.model.Hotel;
import com.epam.model.Reservation;
import com.epam.model.ReservationStatusEnum;
import com.epam.model.TourOffer;
import com.epam.repository.ReservationDAO;
import com.epam.repository.TourOfferDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.*;

@Service
public class ReservationService {
    private final ReservationDAO reservationDAO;
    private final PersonService personService;
    private final HotelService hotelService;
    public int totalAmountOfRows = 4;

    @Autowired
    public ReservationService(ReservationDAO reservationDAO, PersonService personService, HotelService hotelService) {
        this.reservationDAO = reservationDAO;
        this.personService = personService;
        this.hotelService = hotelService;
    }

    public int addReservation(Reservation reservation) {
        if (reservation != null) {
            if (reservation.getClientId() != null && reservation.getStatus() != null) {
                return reservationDAO.addReservation(reservation);
            } else {
                throw new IllegalArgumentException("Some fields are empty");
            }
        } else {
            throw new NoSuchElementException("Such reservation does not exist.");
        }
    }

    Reservation getReservationById(Integer id) {
        if (id != null) {
            if (id > 0) {
                return reservationDAO.getReservationById(id);
            } else {
                throw new IllegalArgumentException("Id must be > 0");
            }
        } else {
            throw new IllegalArgumentException("Id must be specified.");
        }
    }

    public List<Reservation> getReservationsByPersonId(Integer personId) {
        if (personId != null) {
            if (personId > 0) {
                return reservationDAO.getReservationsByPersonId(personId);
            } else {
                throw new IllegalArgumentException("Id must be > 0");
            }
        } else {
            throw new IllegalArgumentException("Id must be specified.");
        }
    }

    public List<Reservation> listReservations(Integer page, Integer total) {

        if (page > 0 && total > 0) {
            if (page > 1) {
                page = (page - 1) * total + 1;
            }
            return reservationDAO.listReservations(page, total);
        } else {
            throw new IllegalArgumentException("Numbers must be integer and > 0");
        }
    }

    public int removeReservation(Integer id) {
        if (id != null) {
            if (id > 0) {
                return reservationDAO.removeReservation(id);
            } else {
                throw new IllegalArgumentException("Id must be > 0");
            }
        } else {
            throw new NoSuchElementException("Reservation with such id does not exist.");
        }
    }
    public int getTourOfferById(Integer tourOfferId) {
        if (tourOfferId != null) {
            if (tourOfferId > 0) {
                return reservationDAO.getTourOfferById(tourOfferId);
            } else {
                throw new IllegalArgumentException("Id must be > 0");
            }
        } else {
            throw new IllegalArgumentException("Tour offer with such id does not exist.");
        }
    }

    int updateReservation(Reservation reservation) {
        if (reservation != null) {
            if (reservation.getId() != null) {
                return reservationDAO.updateReservation(reservation);
            } else {
                throw new IllegalArgumentException("Id must be specified");
            }
        } else {
            throw new NoSuchElementException("There is no such reservation.");
        }
    }

    public int amountOfReservation() {
        return reservationDAO.amountOfReservations();
    }

    public int getTotalPrice(Integer numberOfPeople, Integer pricePerUnit, Integer discountId) {
        if (numberOfPeople > 0 && pricePerUnit > 0 && discountId > 0) {
            return pricePerUnit * numberOfPeople * discountId;
        } else {
            throw new IllegalArgumentException("All arguments must be strictly more than zero");
        }
    }

    public ModelAndView reserveTour(ModelAndView modelAndView, Principal principal, Integer idOfTour, Integer pricePerUnit, Integer numberOfPeople, Integer discountId){
        if (principal == null) {
            modelAndView.setViewName("login");
            modelAndView.addObject("message", "Please, sign in.");
            return modelAndView;
        } else {
            String email = principal.getName();
            modelAndView.setViewName("homepage");
            Integer clientId = personService.getIdByEmail(email);
            Integer totalPrice = getTotalPrice(numberOfPeople, pricePerUnit, discountId);
            addReservation(new Reservation(clientId, idOfTour, numberOfPeople, ReservationStatusEnum.UNPAID, discountId, totalPrice));
            return modelAndView.addObject("message", "Tour was reserved successfully.");
        }
    }

    public Map<Integer,String> getDescription(List<Reservation> reservations, TourOfferService tourOfferService){
        Map<Integer, String> description = new HashMap<>();
        for (Reservation reservation : reservations) {
            Integer tourId = reservation.getTourOfferId();
            TourOffer tourOffer = tourOfferService.getTourById(tourId);
            Integer hotelId = tourOffer.getHotelId();
            Hotel hotel = hotelService.getHotelById(hotelId);
            description.put(reservation.getId(), tourOffer.toString() + hotel.toString());
        }
        return description;
    }
}
