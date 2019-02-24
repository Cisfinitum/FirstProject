package com.epam.repository.interfaces;

import com.epam.model.TourOffer;

import java.time.LocalDate;
import java.util.List;

/**
 * The base interface for database manipulations with tour entity
 *
 * @author Fedor Kevdin
 */
public interface SimpleTourOfferDAO {

    /**
     * Return the list of all tours from the database
     *
     * @return list of tours
     */
    List<TourOffer> getTours();

    /**
     * Get tour id and delete it from the database
     * Return a number of deleted rows
     *
     * @param tourId tour id
     * @return 1 if exactly one tour was deleted
     * 0 if no tours were deleted
     */
    int deleteTour(Integer tourId);

    /**
     * Get the instance of tour and insert it to the database
     * Return a number of new rows
     *
     * @param touroffer instance of tour
     * @return 1 if the tour was inserted successfully
     * @throws NullPointerException if the tour passed as argument was null
     */
    int addTour(TourOffer touroffer);

    /**
     * Get the instance of tour and update its values in the database
     * Return a number of changed rows
     *
     * @param touroffer instance of tour
     * @return 1 if the tour was updated successfully
     * @throws NullPointerException if the tour passed as argument was null
     */
    int updateTour(TourOffer touroffer);

    /**
     * Get the list of hotels' ids, star date or end date and make sql query
     * depending on passed parameters
     * Return the list of tours which match search criteria
     *
     * @param listOfHotelsId list of hotels' ids, can be null
     * @param startDate      start date, can be null
     * @param endDate        end date, can be null
     * @return list of tours
     */
    List<TourOffer> searchTours(List<Integer> listOfHotelsId, LocalDate startDate, LocalDate endDate);
}
