package com.epam.service;

import com.epam.model.Hotel;
import com.epam.repository.HotelDAO;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class HotelServiceImplTest {
    @Mock
    private HotelDAO hotelDAO;
    @Mock
    private Hotel expectedHotel;
    @Mock
    ModelAndView modelAndView;
    @Mock
    RedirectAttributes redirectAttributes;
    private List<Hotel> hotelList;
    Map<Integer,Hotel> expectedHotelsMap;
    @InjectMocks
    private HotelServiceImpl hotelServiceImpl;
    private int expectedResultPositive = 1;
    private String testName = "hotel";
    private String testCountry = "Indonesia";
    private Integer testStars = 5;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        hotelList = new ArrayList<>();
        hotelList.add(expectedHotel);
        expectedHotelsMap = new HashMap<>();
        expectedHotelsMap.put(expectedHotel.getId(),expectedHotel);
        when(expectedHotel.getId()).thenReturn(0);
        when(expectedHotel.getName()).thenReturn("Luxury Hotel");
        when(expectedHotel.getCountry()).thenReturn("Russia");
    }

    @Test
    public void getHotels() {
        when(hotelDAO.getHotels()).thenReturn(hotelList);
        List<Hotel> actualResult = hotelServiceImpl.getHotels();
        assertEquals(hotelList, actualResult);
    }

    @Test (expected = IllegalArgumentException.class)
    @SneakyThrows
    public void getHotelsByCountryThrowsException() {
        hotelServiceImpl.getHotelsByCountry(null);
    }

    @Test
    @SneakyThrows
    public void createHotel() {
        when(hotelDAO.createHotel(expectedHotel)).thenReturn(1);
        int actualResult = hotelServiceImpl.createHotel(expectedHotel);
        assertEquals(actualResult,expectedResultPositive);
    }

    @Test(expected = IllegalArgumentException.class)
    @SneakyThrows
    public void createHotelThrowsException() {
        when(expectedHotel.getName()).thenReturn(null);
        hotelServiceImpl.createHotel(expectedHotel);
    }

    @Test
    @SneakyThrows
    public void updateHotel() {
        when(hotelDAO.updateHotel(expectedHotel)).thenReturn(1);
        int actualResult = hotelServiceImpl.updateHotel(expectedHotel);
        assertEquals(actualResult,expectedResultPositive);
    }

    @Test
    @SneakyThrows
    public void deleteHotel() {
        when(hotelDAO.deleteHotel(1)).thenReturn(1);
        int actualResult = hotelServiceImpl.deleteHotel(1);
        assertEquals(actualResult, expectedResultPositive);
    }

    @Test
    public void addHotel() {
        String testCity = "Bali";
        when(hotelDAO.createHotel(new Hotel(testName, testCountry, testCity, testStars))).thenReturn(1);
        String actualView = hotelServiceImpl.addHotel(modelAndView, testName, testCountry, testCity, testStars, redirectAttributes);
        String expectedView = "redirect:/hotels";
        assertEquals(expectedView, actualView);
    }

    @Test
    public void addHotelWrongValue() {
        String wrongCityName = "777";
        when(hotelDAO.createHotel(new Hotel(testName, testCountry, wrongCityName, testStars))).thenReturn(1);
        String actualView = hotelServiceImpl.addHotel(modelAndView, testName, testCountry, wrongCityName, testStars, redirectAttributes);
        String expectedView = "redirect:/hotels";
        assertEquals(expectedView, actualView);
    }

    @Test
    public void getMapOfHotels(){
        when(hotelServiceImpl.getHotels()).thenReturn(hotelList);
        assertEquals(expectedHotelsMap, hotelServiceImpl.getMapOfHotels());
    }

}

