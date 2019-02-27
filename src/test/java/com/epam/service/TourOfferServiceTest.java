package com.epam.service;

import com.epam.model.Hotel;
import com.epam.model.TourOffer;
import com.epam.repository.TourOfferDAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class TourOfferServiceTest {
    @Mock
    private TourOfferDAO tourOfferDAO;
    @Mock
    private TourOffer expectedTourOffer;
    @Mock
    private HotelService hotelService;
    @Mock
    private ReservationService reservationService;
    @Mock
    private Hotel expectedHotel;

    private List<TourOffer> tourOfferList;

    private TourOfferService tourOfferService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        tourOfferService = new TourOfferService(tourOfferDAO,hotelService,reservationService);
        tourOfferList = new ArrayList<>();
    }

    @Test
    public void deleteTourCheck() {
        when(tourOfferDAO.deleteTour(1)).thenReturn(1);
        assertEquals(tourOfferService.deleteTour(1),1);
    }

    @Test
    public void getToursCheck() {
        when(tourOfferDAO.getTours()).thenReturn(tourOfferList);
        assertEquals(tourOfferService.getTours(),tourOfferList);
    }

    @Test
    public void addTourCheck(){
        when(expectedTourOffer.getDescription()).thenReturn("test");
        when(expectedTourOffer.getPricePerUnit()).thenReturn(1);
        when(expectedTourOffer.getTourType()).thenReturn("test");
        when(expectedTourOffer.getHotelId()).thenReturn(1);
        when(expectedTourOffer.getDiscount()).thenReturn(1);
        when(expectedTourOffer.getEndDate()).thenReturn(LocalDate.now());
        when(expectedTourOffer.getStartDate()).thenReturn(LocalDate.now());
        when(expectedTourOffer.getId()).thenReturn(1);
        when(tourOfferDAO.addTour(expectedTourOffer)).thenReturn(1);
        assertEquals(tourOfferService.addTour(expectedTourOffer),1);
    }

    @Test
    public void updateTourCheck(){
        when(tourOfferDAO.updateTour(expectedTourOffer)).thenReturn(1);
        assertEquals(tourOfferService.updateTour(expectedTourOffer,"test",1,1,"test"),1);
    }

    @Test
    public void searchTourCheck(){
        List<Hotel> expectedHotels = new ArrayList<>();
        expectedHotels.add(expectedHotel);
        when(hotelService.getHotelsByCountry("test")).thenReturn(expectedHotels);
        List<Integer> expectedHotelsId = new ArrayList<>();
        for(Hotel hotel: expectedHotels){
            expectedHotelsId.add(hotel.getId());
        }
        when(tourOfferDAO.searchTours(expectedHotelsId,LocalDate.now(),LocalDate.now())).thenReturn(tourOfferList);
        assertEquals(tourOfferService.searchTours("test",LocalDate.now(),LocalDate.now()),tourOfferList);
    }

    @Test
    public void getTourByIdCheck(){
        when(tourOfferDAO.getTourById(1)).thenReturn(expectedTourOffer);
        assertEquals(tourOfferService.getTourById(1),expectedTourOffer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteTourThrowExceptionNull(){
        when(tourOfferService.deleteTour(null)).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteTourThrowExceptionZero(){
        when(tourOfferService.deleteTour(0)).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTourThrowException(){
        when(tourOfferService.addTour(null)).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateTourThrowException(){
        when(tourOfferService.updateTour(null,"test",1,1,"test")).thenThrow(NullPointerException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getTourByIdThrowExceptionNull(){
        when(tourOfferService.getTourById(null)).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getTourByIdThrowExceptionZero(){
        when(tourOfferService.getTourById(0)).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateTourThrowExceptionTourType(){
        when(tourOfferService.updateTour(expectedTourOffer,null,1,1,"test")).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateTourThrowExceptionPricePerPersonNull(){
        when(tourOfferService.updateTour(expectedTourOffer,"test",null,1,"test")).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateTourThrowExceptionPricePerPersonZero(){
        when(tourOfferService.updateTour(expectedTourOffer,"test",0,1,"test")).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateTourThrowExceptionDiscountNull(){
        when(tourOfferService.updateTour(expectedTourOffer,"test",1,null,"test")).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateTourThrowExceptionDiscountNegative(){
        when(tourOfferService.updateTour(expectedTourOffer,"test",1,-100,"test")).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateTourThrowExceptionTourDescriptiontNull(){
        when(tourOfferService.updateTour(expectedTourOffer,"test",1,1,null)).thenThrow(IllegalArgumentException.class);
    }
}

