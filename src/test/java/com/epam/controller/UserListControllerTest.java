package com.epam.controller;

import com.epam.model.Hotel;
import com.epam.model.Person;
import com.epam.model.Reservation;
import com.epam.service.PersonService;
import com.epam.service.ReservationService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.spi.ResolveResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class UserListControllerTest {
    @InjectMocks
    UserListController userListController;
    @Spy
    ModelMap modelMap;
    @Spy
    ModelAndView modelAndView;
    @Spy
    RedirectAttributes redirectAttributes;
    @Mock
    PersonService personService;
    @Mock
    ReservationService reservationService;
    @Mock
    Person person;
    private String expectedView;
    private String actualView;
    private Integer testNumber = 1;
    private String testMessage = "test";
    private List<Person> personList;
    private List<Reservation> reservations;
    private Map<Integer, String> userMap;

    @Before
    public void setUp() {
        personList = new ArrayList<>();
        reservations = new ArrayList<>();
        userMap = new HashMap<>();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void clientsPage() {
        expectedView = "redirect:/clients/1";
        actualView = userListController.clientsPage();
        assertEquals(expectedView, actualView);
    }

    @Test
    public void listOfUsers() {
        expectedView = "/clients";
        when(personService.listOfUsers(testNumber, testNumber)).thenReturn(personList);
        when(personService.amountOfUsers()).thenReturn(testNumber);
        actualView = userListController.listOfUsers(testNumber, modelMap);
        assertEquals(expectedView, actualView);
    }

    @Test
    public void listOfUsersVerify() {
        when(personService.listOfUsers(testNumber, testNumber)).thenReturn(personList);
        when(personService.amountOfUsers()).thenReturn(testNumber);
        userListController.listOfUsers(testNumber, modelMap);
        verify(personService).listOfUsers(anyInt(), anyInt());
    }

    @Test
    public void listOfUsersVerify2() {
        when(personService.listOfUsers(testNumber, testNumber)).thenReturn(personList);
        when(personService.amountOfUsers()).thenReturn(testNumber);
        userListController.listOfUsers(testNumber, modelMap);
        verify(personService).amountOfUsers();
    }

    @Test
    public void addToBlackList() {
        expectedView = "redirect:/clients";
        when(personService.addToBlackList(testNumber)).thenReturn(true);
        actualView = userListController.addToBlackList(testNumber);
        assertEquals(expectedView, actualView);
    }

    @Test
    public void addToBlackListVerify() {
        when(personService.addToBlackList(testNumber)).thenReturn(true);
        userListController.addToBlackList(testNumber);
        verify(personService).addToBlackList(anyInt());
    }

    @Test
    public void removeFromBlackList() {
        expectedView = "redirect:/clients";
        when(personService.removeFromBlackList(testNumber)).thenReturn(true);
        actualView = userListController.removeFromBlackList(testNumber);
        assertEquals(expectedView, actualView);
    }
    @Test
    public void removeFromBlackListVerify() {
        when(personService.removeFromBlackList(testNumber)).thenReturn(true);
        userListController.removeFromBlackList(testNumber);
        verify(personService).removeFromBlackList(testNumber);
    }

    @Test
    public void payForTour() {
        expectedView = "/clientinfo";
        when(personService.getPersonById(testNumber)).thenReturn(person);
        when(reservationService.changeReservationStatusById(testNumber, testMessage)).thenReturn(1);
        when(reservationService.getReservationsByPersonId(testNumber)).thenReturn(reservations);
        modelAndView = userListController.payForTour(testNumber, testNumber,  modelAndView, redirectAttributes);
        actualView = modelAndView.getViewName();
        assertEquals(expectedView, actualView);
    }
}