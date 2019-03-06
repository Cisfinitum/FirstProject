package com.epam.service;

import com.epam.model.Hotel;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

public interface HotelService {

    /**
     * Get list of all hotels
     * @return list of hotels
     */
    List<Hotel> getHotels();

    /**
     * Get list of hotels by country name
     * @param country string with country name
     * @throws IllegalArgumentException if country name is null
     * @return list of hotels which have country name specified
     */
    List<Hotel> getHotelsByCountry(String country);

    /**
     * Get hotel instance by hotel id
     * @param id hotel id
     * @throws IllegalArgumentException if id is null
     * @return instance of hotel
     */
    Hotel getHotelById(Integer id);

    /**
     * Create hotel instance by calling DAO method
     * @param hotel instance of hotel
     * @throws IllegalArgumentException if country or name are not specified
     * @return 1 if hotel was added successfully
     */
    Integer createHotel(Hotel hotel);

    /**
     * Update hotel instance
     * @param hotel instance of hotel
     * @throws IllegalArgumentException if country or name are not specified
     * @return 1 if hotel was updated successfully
     */
    Integer updateHotel(Hotel hotel);

    /**
     * Delete hotel instance
     * @param id hotel id
     * @throws IllegalArgumentException if id is null
     * @return 1 if hotel was deleted successfully
     */
    Integer deleteHotel(Integer id);

    /**
     * Get map of id and hotel
     * @return map of records with id and hotel
     */
    Map<Integer,Hotel> getMapOfHotels();

    /**
     * Create hotel instance
     * @param modelAndView holder for both Model and View
     * @param name name of hotel
     * @param country country of hotel
     * @param city city of hotel
     * @param stars number of stars
     * @param redirectAttributes redirect attributes
     * @return redirect URI
     */
    String addHotel(ModelAndView modelAndView, String name, String country, String city, Integer stars, RedirectAttributes redirectAttributes);
}
