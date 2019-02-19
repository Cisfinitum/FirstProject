package com.epam.service;

import com.epam.model.Hotel;
import com.epam.repository.HotelDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class HotelServiceTest {
    @Mock
    private HotelDAO hotelDAO;
    private Hotel expectedHotel;
    private List<Hotel> hotelList;
    private HotelService hotelService;
    private int expectedResultPositive = 1;

    private Integer testId = 1;
    private String testHotelName = "Luxury Hotel";
    private String testHotelCity = "Moscow";
    private String testHotelCountry = "Russia";
    private Integer testHotelStars = 5;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        expectedHotel =  new Hotel(1, "Luxury Hotel", "Moscow", "Russia",5);
        hotelService = new HotelService(hotelDAO);
        hotelList = new ArrayList<>();
        hotelList.add(expectedHotel);
    }

    @Test
    public void getHotels() {
        when(hotelDAO.getHotels()).thenReturn(hotelList);
        List<Hotel> actualResult = hotelService.getHotels();
        assertEquals(actualResult,hotelList);
    }

    @Test
    public void createHotel()throws Exception{
        when(hotelDAO.createHotel(expectedHotel)).thenReturn(1);
        int actualResult = hotelService.createHotel(expectedHotel);
        assertEquals(actualResult,expectedResultPositive);
    }

    @Test
    public void updateHotel() throws Exception{
        when(hotelDAO.updateHotel(expectedHotel)).thenReturn(1);
        int actualResult = hotelService.updateHotel(expectedHotel);
        assertEquals(actualResult,expectedResultPositive);
    }

    @Test
    public void deleteHotel() throws Exception {
        when(hotelDAO.deleteHotel(1)).thenReturn(1);
        int actualResult = hotelService.deleteHotel(1);
        assertEquals(actualResult,expectedResultPositive);
    }

}

