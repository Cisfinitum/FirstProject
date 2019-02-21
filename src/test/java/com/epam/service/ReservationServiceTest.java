package com.epam.service;

import com.epam.model.Reservation;
import com.epam.repository.ReservationDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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

    private List<Reservation> expectedReservationsList;
    private Reservation actualReservation;
    private Integer testId = 1;
    private List actualReservationList;
    private int changedItems = 1;
    private int testPage = 1;
    private int testTotal = 20;

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
        when(reservationDAO.listReservations(testPage, testTotal)).thenReturn(expectedReservationsList);
        actualReservationList = reservationService.listReservations(testPage, testTotal);
        assertEquals(expectedReservationsList, actualReservationList);
    }

    @Test
    public void listReservationsReturnsNull() {
        when(reservationDAO.listReservations(testPage, testTotal)).thenReturn(null);
        actualReservationList = reservationService.listReservations(testPage, testTotal);
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
}
