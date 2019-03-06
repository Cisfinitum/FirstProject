package com.epam.controller;

import com.epam.model.Person;
import com.epam.model.Reservation;
import com.epam.service.PersonService;
import com.epam.service.ReservationService;
import com.epam.service.TourOfferService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class ReservationControllerTest {
    @Mock
    RedirectAttributes redirectAttributes;
    @Mock
    ReservationService reservationService;
    @Mock
    PersonService personService;
    @Mock
    TourOfferService tourOfferService;
    @Spy
    ModelMap modelMap;
    @InjectMocks
    ReservationController reservationController;
    private String expectedResult;
    private String actualResult;
    private Integer testNumber = 1;
    private String testStatus = "NEW";
    private List<Reservation> reservations;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        reservations = new ArrayList<>();
    }

    @Test
    public void testadmin() {
        expectedResult = "redirect:/reservation/1";
        actualResult = reservationController.testadmin();
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void deleteReservation() {
        expectedResult = "redirect:/reservation";
        actualResult = reservationController.deleteReservation(testNumber, redirectAttributes);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void deleteReservationVerifyMethod() {
        actualResult = reservationController.deleteReservation(testNumber, redirectAttributes);
        verify(reservationService).removeReservation(anyInt());
    }

    @Test
    public void archiveReservation() {
        expectedResult = "redirect:/reservation";
        actualResult = reservationController.archiveReservation(testNumber, redirectAttributes);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void archiveReservationVerifyMethod() {
        reservationController.archiveReservation(testNumber, redirectAttributes);
        verify(reservationService).changeArchiveStatusById(anyInt(), anyString());
    }

    @Test
    public void restoreReservation() {
        expectedResult = "redirect:/reservation";
        actualResult = reservationController.restoreReservation(testNumber, redirectAttributes);
        assertEquals(expectedResult, actualResult);

    }
    @Test
    public void restoreReservationVerify() {
        reservationController.restoreReservation(testNumber, redirectAttributes);
        verify(reservationService).changeArchiveStatusById(anyInt(), anyString());
    }



    @Test
    public void cleanArchive() {
        expectedResult = "redirect:/reservation";
        actualResult = reservationController.cleanArchive(redirectAttributes);
        assertEquals(expectedResult, actualResult);
    }
    @Test
    public void cleanArchiveVerifyMethod() {
        reservationController.cleanArchive(redirectAttributes);
        verify(reservationService).cleanArchive();
    }

    @Test
    public void listReservation(){
        when(reservationService.listReservations(testNumber, testNumber,  testStatus)).thenReturn(reservations);
        when(personService.mapOfUsersInformation(reservations)).thenReturn(new HashMap<>());
        when(tourOfferService.getDescription(reservations)).thenReturn(new HashMap<>());
        when(reservationService.amountOfReservation(testStatus)).thenReturn(testNumber);
        reservationController.listReservations(testNumber, "", "", modelMap);
        verify(reservationService, atLeast(2)).listReservations(anyInt(), anyInt(), anyString());
    }
    @Test
    public void listReservationVerifyMethod(){
        when(reservationService.listReservations(testNumber, testNumber,  testStatus)).thenReturn(reservations);
        when(personService.mapOfUsersInformation(reservations)).thenReturn(new HashMap<>());
        when(tourOfferService.getDescription(reservations)).thenReturn(new HashMap<>());
        when(reservationService.amountOfReservation(testStatus)).thenReturn(testNumber);
        reservationController.listReservations(testNumber, "", "", modelMap);
        verify(reservationService, atLeast(1)).amountOfReservation(anyString());
    }
    @Test
    public void listReservationVerifyMethod2(){
        when(reservationService.listReservations(testNumber, testNumber,  testStatus)).thenReturn(reservations);
        when(personService.mapOfUsersInformation(reservations)).thenReturn(new HashMap<>());
        when(tourOfferService.getDescription(reservations)).thenReturn(new HashMap<>());
        when(reservationService.amountOfReservation(testStatus)).thenReturn(testNumber);
        reservationController.listReservations(testNumber, "", "", modelMap);
        verify(personService, atLeast(1)).mapOfUsersInformation(anyList());
    }
    @Test
    public void listReservationVerifyMethod3(){
        when(reservationService.listReservations(testNumber, testNumber,  testStatus)).thenReturn(reservations);
        when(personService.mapOfUsersInformation(reservations)).thenReturn(new HashMap<>());
        when(tourOfferService.getDescription(reservations)).thenReturn(new HashMap<>());
        when(reservationService.amountOfReservation(testStatus)).thenReturn(testNumber);
        reservationController.listReservations(testNumber, "", "", modelMap);
        verify(tourOfferService, atLeast(1)).getDescription(anyList());
    }
}