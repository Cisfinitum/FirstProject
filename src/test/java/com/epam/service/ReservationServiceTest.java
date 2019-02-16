package com.epam.service;

import com.epam.model.Reservation;
import com.epam.repository.ReservationDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class ReservationServiceTest {
    @Mock
    private ReservationDAO reservationDAO;
    private ReservationService reservationService;
    private List<Reservation> expectedReservationsList;
    private Reservation expectedReservation;
    private Reservation actualReservation;
    private Integer testId = 1;
    private List actualReservationList;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Integer testClientId = 1;
        Integer testTourOfferId = 1;
        Integer testNumberOfPeople = 1;
        String testStatus = "paid";
        Integer testDiscountId = 1;
        Integer testTotalPrice = 10_000;
        expectedReservation = new Reservation(testId, testClientId, testTourOfferId, testNumberOfPeople, testStatus, testDiscountId, testTotalPrice);
        reservationService = new ReservationService(reservationDAO);
        expectedReservationsList = new ArrayList<>();
        expectedReservationsList.add(expectedReservation);
    }

    @Test
    public void getReservationByIdPositiveCheck() {
        when(reservationDAO.getReservationById(testId)).thenReturn(expectedReservation);
        actualReservation = reservationService.getReservationById(testId);
        assertEquals(actualReservation.getClient_id(), expectedReservation.getClient_id());
    }

    @Test
    public void getReservationReturnsNull() {
        int nonExistId = 2;
        actualReservation = reservationService.getReservationById(nonExistId);
        assertNull(actualReservation);
    }

    @Test
    public void listReservationsPositiveCheck() {
        when(reservationDAO.listReservations()).thenReturn(expectedReservationsList);
        actualReservationList = reservationService.listReservations();
        assertEquals(expectedReservationsList, actualReservationList);
    }

    @Test
    public void listReservationsReturnsNull() {
        when(reservationDAO.listReservations()).thenReturn(null);
        actualReservationList = reservationService.listReservations();
        assertNull(actualReservationList);
    }
}
