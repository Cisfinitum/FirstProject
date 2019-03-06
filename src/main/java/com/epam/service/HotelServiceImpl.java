package com.epam.service;

import com.epam.model.Hotel;
import com.epam.repository.HotelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map;


@Service
public class HotelServiceImpl implements HotelService {

    private final HotelDAO hotelDAO;
    @Autowired
    public HotelServiceImpl(HotelDAO hotelDAO) {
        this.hotelDAO = hotelDAO;
    }

    @Override

    public List<Hotel> getHotels(){ return hotelDAO.getHotels(); }

    @Override
    public List<Hotel> getHotelsByCountry(String country) {
        if (country != null) {
            return hotelDAO.getHotelsByCountry(country);
        }
        else {
            throw new IllegalArgumentException("Country must be specified");
        }
    }

    @Override
    public Hotel getHotelById(Integer id) {
        if (id != null) {
            return hotelDAO.getHotelById(id);
        }
        else {
            throw new IllegalArgumentException("Id must be specified");
        }
    }

    @Override
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

    @Override
    public Integer updateHotel(Hotel hotel) {
        if (hotel.getName() != null) {
            if (hotel.getCountry() != null)
                return hotelDAO.updateHotel(hotel);
            else throw new IllegalArgumentException("Country must be specified");
        } else throw new IllegalArgumentException("Name must be specified");
    }

    @Override
    public Integer deleteHotel(Integer id) {
        if (id != null) {
            return hotelDAO.deleteHotel(id);
        }
        else {
            throw new IllegalArgumentException("Id must be specified");
        }
    }

    @Override
    public Map<Integer,Hotel> getMapOfHotels(){
        List<Hotel> listOfHotels = getHotels();
        Map<Integer, Hotel> hotels = new HashMap<>();
        for(Hotel hotel: listOfHotels){
            hotels.put(hotel.getId(),hotel);
        }
        return hotels;
    }

    @Override
    public String addHotel(ModelAndView modelAndView, String name, String country, String city, Integer stars, RedirectAttributes redirectAttributes){
        Pattern pattern = Pattern.compile("^[a-zA-Z\\-\\s]*$");
        Matcher countryMatcher = pattern.matcher(country);
        Matcher cityMatcher = pattern.matcher(city);
        modelAndView.addObject("hotels", getHotels());
        if (name.equals("")|| !cityMatcher.matches() || city.equals("") || !countryMatcher.matches() || country.equals("")) {
            redirectAttributes.addFlashAttribute("name", name);
            redirectAttributes.addFlashAttribute("country", country);
            redirectAttributes.addFlashAttribute("city", city);
            redirectAttributes.addFlashAttribute("errormessage", "Please, fill every field with valid values");
            return "redirect:/hotels";

        }
        if (createHotel(new Hotel(name.trim(), country.trim(), city.trim(), stars)) != 1) {
            redirectAttributes.addFlashAttribute("name", name);
            redirectAttributes.addFlashAttribute("country", country);
            redirectAttributes.addFlashAttribute("city", city);
            redirectAttributes.addFlashAttribute("errormessage", "Some field are empty");
            return "redirect:/hotels";
        }
        redirectAttributes.addFlashAttribute("message", "Hotel was created successfully");
        return "redirect:/hotels";

    }

}