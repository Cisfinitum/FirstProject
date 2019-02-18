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
        return reservationDAO.addReservation(reservation);
    }

    Reservation getReservationById(Integer id) {
        return reservationDAO.getReservationById(id);
    }

    public List<Reservation> listReservations() {
        return reservationDAO.listReservations();
    }

    public int removeReservation(Integer id) {
        return reservationDAO.removeReservation(id);
    }

    int getTourOfferById(Integer tourOfferId) {
        return reservationDAO.getTourOfferById(tourOfferId);
    }

    int updateReservation(Reservation reservation) {
        return reservationDAO.updateReservation(reservation);
    }

}
