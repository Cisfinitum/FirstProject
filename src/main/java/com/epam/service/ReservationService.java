package com.epam.service;

import com.epam.model.Reservation;
import com.epam.repository.ReservationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    private final ReservationDAO reservationDAO;


    @Autowired
    public ReservationService(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }


    int addReservation(Reservation reservation) {
        if (reservation != null) {
            return reservationDAO.addReservation(reservation);
        } else {
            throw new RuntimeException("Some fields are empty");
        }
    }

    Reservation getReservationById(Integer id) {
        if (id != null) {
            if (id >= 0) {
                return reservationDAO.getReservationById(id);
            } else {
                throw new IllegalArgumentException("Id must be >= 0");
            }
        } else {
            throw new RuntimeException("Id must be specified.");
        }
    }

    public List<Reservation> listReservations() {
        return reservationDAO.listReservations();
    }

    public int removeReservation(Integer id) {
        if (id != null) {
            if (id >= 0) {
                return reservationDAO.removeReservation(id);
            } else {
                throw new IllegalArgumentException("Id must be >= 0");
            }
        } else {
            throw new RuntimeException("Reservation with such id does not exist.");
        }
    }

    int getTourOfferById(Integer tourOfferId) {
        if (tourOfferId != null) {
            if (tourOfferId >= 0) {
        return reservationDAO.getTourOfferById(tourOfferId);
            } else {
                throw new IllegalArgumentException("Id must be >= 0");
            }
        } else {
            throw new RuntimeException("Tour offer with such id does not exist.");
        }
    }

    int updateReservation(Reservation reservation) {
        if(reservation != null) {
            return reservationDAO.updateReservation(reservation);
        }else {
            throw new RuntimeException("There is no such reservation.");
        }
    }
}
