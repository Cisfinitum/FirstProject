package com.epam.service;

import com.epam.model.Reservation;
import com.epam.repository.ReservationDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class ReservationServiceTest {
    @Mock
    private ReservationDAO reservationDAO;
    @Mock
    private Reservation expectedReservation;
    @InjectMocks
    ReservationService reservationService;
    @Mock
    Principal principal;
    @Spy
    ModelAndView modelAndView;
    @Mock
    PersonService personService;
    private int testPage = 1;
    private int testTotal = 5;
    private List<Reservation> expectedReservationsList;
    private Reservation actualReservation;
    private Integer testId = 1;
    private List actualReservationList;
    private int changedItems = 1;
    private Integer testDiscountId = 1;
    private Integer numberOfPeople = 5;
    private Integer testPricePerUnit = 100;
    private String testStatus = "NEW";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        expectedReservationsList = new ArrayList<>();
        expectedReservationsList.add(expectedReservation);
    }

    @Test
    public void getReservationById() {
        when(reservationDAO.getReservationById(testId)).thenReturn(expectedReservation);
        Integer testClientId = 1;
        when(expectedReservation.getClientId()).thenReturn(testClientId);
        actualReservation = reservationService.getReservationById(testId);
        assertEquals(actualReservation.getClientId(), expectedReservation.getClientId());
    }

    @Test
    public void getReservationReturnsNull() {
        int nonExistId = 2;
        actualReservation = reservationService.getReservationById(nonExistId);
        assertNull(actualReservation);
    }

    @Test
    public void listReservations() {
        when(reservationDAO.listReservations(testPage, testTotal, testStatus)).thenReturn(expectedReservationsList);
        actualReservationList = reservationService.listReservations(testPage, testTotal, testStatus);
        assertEquals(expectedReservationsList, actualReservationList);
    }

    @Test
    public void listReservationsReturnsNull() {
        when(reservationDAO.listReservations(testPage, testTotal, testStatus)).thenReturn(null);
        actualReservationList = reservationService.listReservations(testPage, testTotal, testStatus);
        assertNull(actualReservationList);
    }

//    @Test
//    public void addReservation() {
//        when(reservationDAO.addReservation(expectedReservation)).thenReturn(changedItems);
//        assertEquals(changedItems, reservationService.addReservation(expectedReservation));
//    }

    @Test
    public void removeReservation() {
        when(reservationDAO.removeReservation(changedItems)).thenReturn(testId);
        assertEquals(changedItems, reservationService.removeReservation(testId));
    }

    @Test
    public void updateReservation() {
        when(reservationDAO.updateReservation(expectedReservation)).thenReturn(changedItems);
        assertEquals(changedItems, reservationService.updateReservation(expectedReservation));
    }

    @Test
    public void getTotalPrice() {
        Integer expectedTotalPrice = 5;
        Integer actualTotalPrice = reservationService.getTotalPrice(numberOfPeople, testPricePerUnit, testDiscountId);
        assertEquals(expectedTotalPrice, actualTotalPrice);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getTotalPriceThrowsIllegalArgumentException() {
        Integer wrongPricePerUnit = -1;
        reservationService.getTotalPrice(numberOfPeople, wrongPricePerUnit, testDiscountId);
    }

    @Test
    public void reserveTour() {
        String expectedView = "homepage";
        String testEmail = "user";
        when(principal.getName()).thenReturn(testEmail);
        when(personService.getIdByEmail(testEmail)).thenReturn(1);
        modelAndView = reservationService.reserveTour(modelAndView, principal, testId, testPricePerUnit, numberOfPeople, testDiscountId);
        assertEquals(expectedView , modelAndView.getViewName());
    }

    @Test
    public void reserveTourUserDidNotLogIn(){
        principal = null;
        String expectedView = "login";
        modelAndView = reservationService.reserveTour(modelAndView, principal, testId, testPricePerUnit, numberOfPeople, testDiscountId);
        assertEquals(expectedView , modelAndView.getViewName());
    }
}
