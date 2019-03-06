package com.epam.controller;

import com.epam.model.Hotel;
import com.epam.service.HotelService;
import com.epam.service.TourOfferService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class HotelControllerTest {
    @InjectMocks
    HotelController hotelController;
    @Spy
    ModelMap modelMap;
    @Spy
    ModelAndView modelAndView;
    @Mock
    TourOfferService tourOfferService;
    @Spy
    RedirectAttributes redirectAttributes;
    @Mock
    HotelService hotelService;
    private String expectedView;
    private String actualView;
    private Integer testNumber = 1;
    private String testMessage = "test";
    private List<Hotel> hotels;
    private Map<Integer, Boolean> hotelMap;

    @Before
    public void setUp() {
        hotels = new ArrayList<>();
        hotelMap = new HashMap<>();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void hotelPage() {
        expectedView = "hotels";
        when(hotelService.getHotels()).thenReturn(hotels);
        when(tourOfferService.getMapOfHotelUse(hotels)).thenReturn(hotelMap);
        actualView = hotelController.hotelPage(testMessage, testMessage, testMessage, testMessage, testMessage, modelMap);
        assertEquals(expectedView, actualView);
    }
    @Test
    public void hotelPageVerify() {
        when(hotelService.getHotels()).thenReturn(hotels);
        when(tourOfferService.getMapOfHotelUse(hotels)).thenReturn(hotelMap);
        hotelController.hotelPage(testMessage, testMessage, testMessage, testMessage, testMessage, modelMap);
        verify(hotelService).getHotels();
    }
    @Test
    public void hotelPageVerify2() {
        when(hotelService.getHotels()).thenReturn(hotels);
        when(tourOfferService.getMapOfHotelUse(hotels)).thenReturn(hotelMap);
        hotelController.hotelPage(testMessage, testMessage, testMessage, testMessage, testMessage, modelMap);
        verify(tourOfferService).getMapOfHotelUse(anyList());
    }

    @Test
    public void deleteReservation() {
        expectedView = "redirect:/hotels";
        actualView = hotelController.deleteReservation(testNumber, redirectAttributes);
        assertEquals(expectedView, actualView);
    }

    @Test
    public void addHotel() {
        expectedView = "redirect:/hotels";
        when(hotelService.addHotel(modelAndView, testMessage, testMessage, testMessage,testNumber, redirectAttributes)).thenReturn("redirect:/hotels");
        actualView = hotelController.addHotel(testMessage, testMessage, testMessage, testNumber, modelAndView, redirectAttributes);
        assertEquals(expectedView, actualView);
    }
    @Test
    public void addHotelVerify() {
        expectedView = "redirect:/hotels";
        when(hotelService.addHotel(modelAndView, testMessage, testMessage, testMessage,testNumber, redirectAttributes)).thenReturn("redirect:/hotels");
        hotelController.addHotel(testMessage, testMessage, testMessage, testNumber, modelAndView, redirectAttributes);
        verify(hotelService).addHotel(modelAndView, testMessage, testMessage, testMessage,testNumber, redirectAttributes);
    }
}