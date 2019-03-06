package com.epam.service;

import com.epam.model.Reservation;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

public interface ReservationService {
    int totalAmountOfRows = 4;

    /**
     * Get the instance of a reservation and add it to the database using DAO layer
     *
     * @param reservation the instance of a reservation
     * @return 1 if reservation was added successfully
     * @throws IllegalArgumentException         if clientId and status of the reservation are null
     * @throws java.util.NoSuchElementException if the instance of the reservation is null
     */
    int addReservation(Reservation reservation);


    /**
     * Get id and return the instance of the reservation with specified id
     *
     * @param id reservation id
     * @return the instance of a reservation
     * @throws IllegalArgumentException if the id is negative
     * @throws IllegalArgumentException if the the id is null
     */
    Reservation getReservationById(Integer id);


    /**
     * Get person id and return the list of reservations with specified person id
     *
     * @param personId person id
     * @return the list of the reservation
     * @throws IllegalArgumentException if the person id is negative
     * @throws IllegalArgumentException if the person id is null
     */
    List<Reservation> getReservationsByPersonId(Integer personId);


    /**
     * Get number of page, total amount of rows on one page and reservation status
     * Return the list of reservations with specified status
     *
     * @param page   number of the page
     * @param total  total amount of rows on one page
     * @param status reservation status
     * @return list of reservation
     * @throws IllegalArgumentException if page or total are negative
     */
    List<Reservation> listReservations(Integer page, Integer total, String status);


    /**
     * Get reservation id and delete it from the database using DAO layer
     *
     * @param id reservation id
     * @return 1 if one reservation was deleted
     * @throws IllegalArgumentException         if yhe id is negative
     * @throws java.util.NoSuchElementException if reservation with such id does not exist
     */
    int removeReservation(Integer id);


    /**
     * Get tour offer id and check if there are reservations with such tour offer id in the
     * database using DAO layer return 0 if there are no such reservations
     * 1 if there are such reservations in the database
     *
     * @param tourOfferId tour offer id
     * @return 1 if reservations with such id exist
     * 0 if such reservations don't exist
     * @throws IllegalArgumentException if tour offer id is null
     * @throws IllegalArgumentException if tour offer id is negative
     */
    int getTourOfferById(Integer tourOfferId);


    /**
     * Get the instance of a reservation and update its values in the database using DAO layer
     *
     * @param reservation the instance of a reservation
     * @return 1 if one reservation was updated
     * @throws IllegalArgumentException         if the instance of a reservation is null
     * @throws java.util.NoSuchElementException if such reservation does not exist
     */
    int updateReservation(Reservation reservation);


    /**
     * Get status of a reservation and return the amount of reservation with such status
     *
     * @param status reservation status
     * @return amount of reservations with specified status
     */
    int amountOfReservation(String status);


    /**
     * Return amount of rows in the reservation table
     *
     * @return all amount of rows
     */
    int amountOfAllReservations();


    /**
     * Get number of people, price per one person and amount of discount and calculate total amount according to the parameters
     *
     * @param numberOfPeople number of people
     * @param pricePerUnit   price per one person
     * @param discount       amount of discount
     * @return total price of tour
     * @throws IllegalArgumentException if at least one of parameter is negative
     */
    int getTotalPrice(Integer numberOfPeople, Integer pricePerUnit, Integer discount);


    /**
     * Get reservation id and reservation status, and change reservation status in reservation with specified id
     *
     * @param id     reservation id
     * @param status reservation status
     * @return 1 if exactly one reservation status was updated
     * @throws IllegalArgumentException         if id is null
     * @throws java.util.NoSuchElementException if reservation with specified id does not exist
     */
    int changeReservationStatusById(Integer id, String status);


    /**
     * Delete all reservations from the database using DAO layer with status "ARCHIVED"
     *
     * @return amount of deleted reservations
     */
    int cleanArchive();

    /**
     * Get reservation id and reservation status, and change reservation status in reservation with specified id
     *
     * @param id     reservation id
     * @param status reservation status
     * @return 1 if exactly one reservation status was updated
     * @throws IllegalArgumentException         if id is null
     * @throws java.util.NoSuchElementException if reservation with specified id does not exist
     */
    int changeArchiveStatusById(Integer id, String status);

    /**
     * Get parameters, calculate total price and change reservation status to paid or unpaid
     *
     * @param modelAndView       model and view
     * @param principal          principal
     * @param idOfTour           tour offer id
     * @param pricePerUnit       price per person
     * @param numberOfPeople     number of people
     * @param discount           discount
     * @param redirectAttributes redirect attributes
     * @return model and view
     */
    ModelAndView reserveTour(ModelAndView modelAndView, Principal principal, Integer idOfTour, Integer pricePerUnit, Integer numberOfPeople, Integer discount, RedirectAttributes redirectAttributes);
}
