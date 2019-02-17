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

    public void createHotel(Hotel hotel) {
        hotelDAO.createHotel(hotel);
    }

    public void updateHotel (Hotel hotel) {
        hotelDAO.updateHotel(hotel);
    }

    public void deleteHotel (Hotel hotel) {
        hotelDAO.deleteHotel(hotel.getId());
    }

}