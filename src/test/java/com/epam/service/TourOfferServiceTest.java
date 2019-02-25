package com.epam.service;

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

    private List<TourOffer> tourOfferList;

    private TourOfferService tourOfferService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        tourOfferService = new TourOfferService(tourOfferDAO);
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
        when(tourOfferDAO.addTour(expectedTourOffer)).thenReturn(1);
        assertEquals(tourOfferService.addTour(expectedTourOffer),1);
    }

    @Test
    public void updateTourCheck(){
        when(tourOfferDAO.updateTour(expectedTourOffer)).thenReturn(1);
        assertEquals(tourOfferService.updateTour(expectedTourOffer),1);
    }
    @Test
    public void searchTourCheck(){
        when(tourOfferDAO.searchTours(new ArrayList<>(),LocalDate.now(),LocalDate.now())).thenReturn(tourOfferList);
        assertEquals(tourOfferService.searchTours("test",LocalDate.now(),LocalDate.now()),tourOfferList);
    }

    @Test(expected = NullPointerException.class)
    public void deleteTourThrowExceptionNull(){
        when(tourOfferService.deleteTour(null)).thenThrow(NullPointerException.class);
    }

    @Test(expected = NullPointerException.class)
    public void deleteTourThrowExceptionZero(){
        when(tourOfferService.deleteTour(0)).thenThrow(NullPointerException.class);
    }

    @Test(expected = NullPointerException.class)
    public void addTourThrowException(){
        when(tourOfferService.addTour(null)).thenThrow(NullPointerException.class);
    }

    @Test(expected = NullPointerException.class)
    public void updateTourThrowException(){
        when(tourOfferService.updateTour(null)).thenThrow(NullPointerException.class);
    }
}

