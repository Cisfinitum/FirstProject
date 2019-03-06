package com.epam.controller;

import com.epam.service.ReservationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PaymentControllerTest {
    @Mock
    ReservationService reservationService;
    @Spy
    RedirectAttributes redirectAttributes;
    @Spy
    ModelAndView modelAndView;
    @InjectMocks
    PaymentController paymentController;
    private String expectedView;
    private String actualView;
    private Integer testNumber = 1;
    private String testMessage = "test";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void payForTour() {
        when(reservationService.changeArchiveStatusById(testNumber, testMessage)).thenReturn(1);
        paymentController.payForTour(testNumber, modelAndView, redirectAttributes);
        verify(reservationService).changeReservationStatusById(anyInt(), anyString());
    }

    @Test
    public void payForTourCheckView() {
        expectedView = "redirect:/clientProfile";
        when(reservationService.changeArchiveStatusById(testNumber, testMessage)).thenReturn(1);
        modelAndView = paymentController.payForTour(testNumber, modelAndView, redirectAttributes);
        actualView = modelAndView.getViewName();
        assertEquals(expectedView, actualView);
    }

    @Test
    public void payForTour1() {
        expectedView = "redirect:/clientProfile";
        modelAndView = paymentController.payForTour(modelAndView, redirectAttributes);
        actualView = modelAndView.getViewName();
        assertEquals(expectedView, actualView);
    }

    @Test
    public void payForTour1CheckMessages() {
        expectedView = "payment";
        modelAndView = paymentController.payForTour(testNumber, testNumber, testNumber, testNumber, testNumber, modelAndView);
        actualView = modelAndView.getViewName();
        assertEquals(expectedView, actualView);
    }

    @Test
    public void cancelReservation() {
        expectedView = "redirect:/clientProfile";
        modelAndView = paymentController.cancelReservation(testNumber, modelAndView, redirectAttributes);
        actualView = modelAndView.getViewName();
        assertEquals(expectedView, actualView);
    }
}