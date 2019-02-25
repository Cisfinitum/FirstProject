package com.epam.repository.interfaces;

import com.epam.model.Hotel;

import java.util.List;

/**
 * The base interface for database manipulations with hotel entity
 *
 * @author Ilia Grigorev
 */
public interface SimpleHotelDAO {
    /**
     * Return the list of all hotels from the database
     *
     * @return the list of hotels
     */
    List<Hotel> getHotels();

    /**
     * Get country name if there are no hotels with such country in database return empty list
     * else return the list of hotels with specified country
     *
     * @param country country name
     * @return the list of hotels with specified country
     */
    List<Hotel> getHotelsByCountry(String country);

    /**
     * Get hotel id and return hotel from database
     * if there is no hotel with such id in the database throw EmptyResultDataAccessException
     *
     * @param id hotel id
     * @return hotel with specified id
     * @throws org.springframework.dao.EmptyResultDataAccessException if hotel with specified id does not exist
     */
    Hotel getHotelById(Integer id);

    /**
     * Get the instance of hotel and insert it to the database
     * Return a number of new rows
     *
     * @param hotel instance of the hotel
     * @return 1 if the hotel was inserted successfully
     * @throws NullPointerException if the hotel passed as argument was null
     */
    Integer createHotel(Hotel hotel);

    /**
     * Get the instance of the hotel and update its values in the database
     * Return a number of changed rows
     *
     * @param hotel instance of the hotel
     * @return 1 if the hotel is updated successfully
     * @throws NullPointerException if the hotel passed as argument was null
     */
    Integer updateHotel(Hotel hotel);

    /**
     * Get the hotel id and delete a hotel with such id from the database
     * Return a number of deleted rows
     *
     * @param id hotel id
     * @return 1 if one hotel was deleted
     *      * 0 if no hotels were deleted
     */
    Integer deleteHotel(int id);
}
