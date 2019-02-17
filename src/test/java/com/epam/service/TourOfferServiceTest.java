package com.epam.service;

import com.epam.model.TourOffer;
import com.epam.repository.TourOfferDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class TourOfferServiceTest {
    @Mock
    private TourOfferDAO tourOfferDAO;

    private List<TourOffer> tourOfferList;
    private TourOfferService tourOfferService;
    private TourOffer expectedTourOffer;
    private int expectedResultPositive;
    private int expectedResultNegative;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        expectedTourOffer = new TourOffer(1, "Active", Date.valueOf("2018-02-19"),  Date.valueOf("2018-02-25"),
                1500, 1,"Best tour", 1);
        tourOfferService = new TourOfferService(tourOfferDAO);
        tourOfferList = new ArrayList<>();
        tourOfferList.add(expectedTourOffer);
        expectedResultPositive = 1;
        expectedResultNegative = 0;
    }

    @Test
    public void deleteTourPositiveResult() {
        when(tourOfferDAO.deleteTour(1)).thenReturn(1);
        int actualResulat = tourOfferService.deleteTour(1);
        assertEquals(actualResulat,expectedResultPositive);
    }

    @Test
    public void deleteTourNegativeResult() {
        when(tourOfferDAO.deleteTour(1)).thenReturn(0);
        int actualResulat = tourOfferService.deleteTour(1);
        assertEquals(actualResulat,expectedResultNegative);
    }

    @Test
    public void getToursPositiveResult() {
        when(tourOfferDAO.getTours()).thenReturn(tourOfferList);
        List<TourOffer> actualResulat = tourOfferService.getTours();
        assertEquals(actualResulat,tourOfferList);
    }

    @Test
    public void addTourPositiveResult(){
        when(tourOfferDAO.addTour(expectedTourOffer)).thenReturn(1);
        int actualResulat = tourOfferService.addTour(1, "Active", Date.valueOf("2018-02-19"),  Date.valueOf("2018-02-25"),
                1500, 1,"Best tour", 1);
        assertEquals(actualResulat,expectedResultPositive);
    }

    @Test
    public void addTourNegativeResult(){
        when(tourOfferDAO.addTour(expectedTourOffer)).thenReturn(0);
        int actualResulat = tourOfferService.addTour(1, "Active", Date.valueOf("2018-02-19"),  Date.valueOf("2018-02-25"),
                1500, 1,"Best tour", 1);
        assertEquals(actualResulat,expectedResultNegative);
    }

    @Test
    public void updateTourPositiveResult(){
        when(tourOfferDAO.updateTour(expectedTourOffer)).thenReturn(1);
        int actualResulat = tourOfferService.updateTour(1, "Active", Date.valueOf("2018-02-19"),  Date.valueOf("2018-02-25"),
                1500, 1,"Best tour", 1);
        assertEquals(actualResulat,expectedResultPositive);
    }

    @Test
    public void updateTourNegativeResult(){
        when(tourOfferDAO.updateTour(expectedTourOffer)).thenReturn(0);
        int actualResulat = tourOfferService.updateTour(1, "Active", Date.valueOf("2018-02-19"),  Date.valueOf("2018-02-25"),
                1500, 1,"Best tour", 1);
        assertEquals(actualResulat,expectedResultNegative);
    }

}

