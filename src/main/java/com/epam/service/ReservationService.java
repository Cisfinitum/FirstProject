package com.epam.service;

import com.epam.model.Reservation;
import com.epam.repository.ReservationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReservationService {
    private final ReservationDAO reservationDAO;
    public int totalAmountOfRows = 4;

    @Autowired
    public ReservationService(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
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

    int getTourOfferById(Integer tourOfferId) {
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
}
