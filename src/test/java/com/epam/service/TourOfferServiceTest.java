package com.epam.service;

import com.epam.model.Person;
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
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

public class TourOfferServiceTest {
    @Mock
    private TourOfferDAO tourOfferDAO;

    private List<TourOffer> tourOfferList;
    private TourOfferService tourOfferService;
    private TourOffer expectedTourOffer;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        expectedTourOffer = new TourOffer(1, "Active", Date.valueOf("20.02.19"),  Date.valueOf("26.02.19"),
                1500, 1,"Best tour", 1);
        tourOfferService = new TourOfferService(tourOfferDAO);
        tourOfferList = new ArrayList<>();
        tourOfferList.add(expectedTourOffer);
    }
//
//    @Test
//    public void getToursPositiveResult() {
//        String name = "user";
//        when(personDAO.getPersons()).thenReturn(personList);
//        Person actualPerson = personService.getPerson(name);
//        assertEquals(expectedPerson, actualPerson);
//    }
//
//    @Test
//    public void getToursNotFound() {
//        String name = "tmp";
//        when(personDAO.getPersons()).thenReturn(personList);
//        Person actualPerson = personService.getPerson(name);
//        assertNull(actualPerson);
//    }
}

