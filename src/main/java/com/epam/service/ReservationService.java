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


    public void addReservation(Reservation reservation) {
        reservationDAO.addReservation(reservation);
    }

    public Reservation getReservationById(Integer id) {
        return reservationDAO.getReservationById(id);
    }

    public List listReservations() {
        List<Reservation> reservations = reservationDAO.listReservations();
        return reservations;
    }

    public void removeReservation(Integer id) {
        reservationDAO.removeReservation(id);
    }

    public void updateReservation(Integer id, Integer Client_id, Integer TourOffer_id, Integer NumberOfPeople, String Status, Integer Discount_id, Integer TotalPrice) {
    reservationDAO.updateReservation(id,Client_id,TourOffer_id,NumberOfPeople,Status,Discount_id,TotalPrice);
    }

}
