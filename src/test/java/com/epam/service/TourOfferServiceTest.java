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
    @Mock
    private TourOffer expectedTourOffer;
    private String testtourType = "Active";
    private Date teststartDate = Date.valueOf("2018-02-19");
    private Date testendDate = Date.valueOf("2018-02-25");
    private Integer testpricePerUnit = 1500;
    private Integer testhotel_id = 1;
    private String testdescription = "Best tour";
    private Integer testdiscount_id = 1;
    private List<TourOffer> tourOfferList;
    private TourOfferService tourOfferService;
    private int expectedResultPositive = 1;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        tourOfferService = new TourOfferService(tourOfferDAO);
        tourOfferList = new ArrayList<>();
        tourOfferList.add(expectedTourOffer);
    }

    @Test
    public void deleteTourCheck() {
        when(tourOfferDAO.deleteTour(1)).thenReturn(1);
        int actualResulat = tourOfferService.deleteTour(1);
        assertEquals(actualResulat,expectedResultPositive);
    }


    @Test
    public void getToursCheck() {
        when(tourOfferDAO.getTours()).thenReturn(tourOfferList);
        List<TourOffer> actualResulat = tourOfferService.getTours();
        assertEquals(actualResulat,tourOfferList);
    }

    @Test
    public void addTourCheck(){
        when(tourOfferDAO.addTour(expectedTourOffer)).thenReturn(1);
        int actualResulat = tourOfferService.addTour(1, testtourType, teststartDate,  testendDate,
                testpricePerUnit, testhotel_id,testdescription, testdiscount_id);
        assertEquals(actualResulat,expectedResultPositive);
    }

    @Test
    public void updateTourCheck(){
        when(tourOfferDAO.updateTour(expectedTourOffer)).thenReturn(1);
        int actualResulat = tourOfferService.updateTour(1, testtourType, teststartDate,  testendDate,
                testpricePerUnit, testhotel_id,testdescription, testdiscount_id);
        assertEquals(actualResulat,expectedResultPositive);
    }
}

