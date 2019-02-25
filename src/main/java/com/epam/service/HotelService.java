package com.epam.service;

import com.epam.model.Hotel;
import com.epam.repository.HotelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map;


@Service
public class HotelService {

    private final HotelDAO hotelDAO;
    @Autowired
    public HotelService(HotelDAO hotelDAO) {
        this.hotelDAO = hotelDAO;
    }

    public List<Hotel> getHotels(){ return hotelDAO.getHotels(); }

    public List<Hotel> getHotelsByCountry (String country) {
        if (country != null) {
            return hotelDAO.getHotelsByCountry(country);
        }
        else {
            throw new IllegalArgumentException("Country must be specified");
        }
    }

    public Hotel getHotelById (Integer id) {
        if (id != null) {
            return hotelDAO.getHotelById(id);
        }
        else {
            throw new IllegalArgumentException("Id must be specified");
        }
    }

    public Integer createHotel(Hotel hotel) {
        if (hotel.getName() != null) {
            if (hotel.getCountry() != null) {
                return hotelDAO.createHotel(hotel);
            }
            else {
                throw new IllegalArgumentException("Country must be specified");
            }
        } else {
            throw new IllegalArgumentException("Name must be specified");
        }
    }

    public Integer updateHotel (Hotel hotel) {
        if (hotel.getName() != null) {
            if (hotel.getCountry() != null)
                return hotelDAO.updateHotel(hotel);
            else throw new IllegalArgumentException("Country must be specified");
        } else throw new IllegalArgumentException("Name must be specified");
    }

    public Integer deleteHotel (Integer id) {
        if (id != null) {
            return hotelDAO.deleteHotel(id);
        }
        else {
            throw new IllegalArgumentException("Id must be specified");
        }
    }

    public Map<Integer,Hotel> getMapOfHotels(){
        List<Hotel> listOfHotels = getHotels();
        Map<Integer, Hotel> hotels = new HashMap<>();
        for(Hotel hotel: listOfHotels){
            hotels.put(hotel.getId(),hotel);
        }
        return hotels;
    }

    public ModelAndView addHotel(ModelAndView modelAndView, String name, String country, String city, Integer stars ){
        Pattern pattern = Pattern.compile("^[a-zA-Z ]*$");
        Matcher countryMatcher = pattern.matcher(country);
        Matcher cityMatcher = pattern.matcher(city);
        if (name.equals("")) {
            return modelAndView.addObject("errormessage", "Name is empty");
        }
        if (!cityMatcher.matches() || city.equals("")) {
            return modelAndView.addObject("errormessage", "Invalid name for city");
        }
        if (!countryMatcher.matches() || country.equals("")) {
            return modelAndView.addObject("errormessage", "Invalid name for country");
        }
        if (createHotel(new Hotel(name.trim(), country.trim(), city.trim(), stars)) != 1) {
            return modelAndView.addObject("errormessage", "Some field are empty");
        }
        return modelAndView.addObject("message", "Hotel was created successfully");
    }

}