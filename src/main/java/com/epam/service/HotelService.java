package com.epam.service;

import com.epam.model.Hotel;
import com.epam.repository.HotelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class HotelService {

    private final HotelDAO hotelDAO;
    @Autowired
    public HotelService(HotelDAO hotelDAO) {
        this.hotelDAO = hotelDAO;
    }

    public List<Hotel> getHotels(){ return hotelDAO.getHotels(); }

    public List<Hotel> getHotelsByCountry (String country) { return hotelDAO.getHotelsByCountry(country); }

    public Hotel getHotelById (Integer id) { return hotelDAO.getHotelById(id); }

    public Integer createHotel(Hotel hotel) {
        return hotelDAO.createHotel(hotel);
    }

    public Integer updateHotel (Hotel hotel) {
        return hotelDAO.updateHotel(hotel);
    }

    public Integer deleteHotel (Hotel hotel) {
        return hotelDAO.deleteHotel(hotel.getId());
    }

}