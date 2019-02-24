package com.epam.repository.interfaces;

import com.epam.model.Reservation;

import java.util.List;

/**
 * The base interface for database manipulations with reservation entity
 *
 * @author Kseniia Maltseva
 */
public interface SimpleReservationDAO {
    /**
     * Get the instance of reservation and insert it to database
     * Return a number of new rows
     *
     * @param reservation the instance of reservation
     * @return 1 if exactly one hotel was inserted
     * @throws NullPointerException if the hotel instance passed as argument was null
     */
    int addReservation(Reservation reservation);

    /**
     * Get reservation id and return reservation with specified id
     * if there is no reservation with such id throw EmptyResultDataAccessException
     *
     * @param reservationId reservation id
     * @return reservation with specified id
     * @throws org.springframework.dao.EmptyResultDataAccessException if hotel with specified id does not exist
     */
    Reservation getReservationById(Integer reservationId);

    /**
     * Get tour offer id and check if there are some reservations which depends on
     * tour offer with specified id in the database
     *
     * @param offerId tour offer id
     * @return 1 if there are reservations which depends on tour offer with such id in the database
     * 0 if there is no such reservation
     */
    int getTourOfferById(Integer offerId);

    /**
     * Get number of page and amount of rows on one page
     * Return the list of reservations which represent one page of table
     *
     * @return list of reservations
     */
    List<Reservation> listReservations(Integer page, Integer total);

    /**
     * Get reservation id and delete it from the database
     * Return a number of deleted rows
     *
     * @param reservationId reservation id
     * @return 1 if one reservation was deleted
     * 0 if no reservations were deleted
     */
    int removeReservation(Integer reservationId);

    /**
     * Get the instance of reservation and update its values
     * Return a number of updated rows
     *
     * @param reservation the instance of reservation
     * @return 1 if reservation was updated successfully
     * @throws NullPointerException if reservation passed as argument is null
     */
    int updateReservation(Reservation reservation);

    /**
     * Return a number of rows in the table "reservation"
     *
     * @return number of rows
     */
    int amountOfReservations();
}
