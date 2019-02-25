package com.epam.service;


import com.epam.model.Person;
import com.epam.model.PersonRoleEnum;
import com.epam.repository.PersonDAO;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

public class PersonServiceTest {
    @Mock
    private PersonDAO personDAO;

    private List<Person> personList;
    private PersonService personService;
    private Person expectedPerson;
    private String testEmail;
    private String testPassword;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testEmail = "user";
        testPassword = "1111";
        expectedPerson = new Person(1, testEmail, testPassword, PersonRoleEnum.valueOf("ADMIN"));
        personService = new PersonService(personDAO);
        personList = new ArrayList<>();
        personList.add(expectedPerson);
    }

    @Test
   public void getPersonPositiveResult() {
        String email = "user";
        when(personDAO.getPersons()).thenReturn(personList);
        Person actualPerson = personService.getPerson(email);
        assertEquals(expectedPerson, actualPerson);
    }

    @Test
    public void getPersonNotFound() {
        String name = "isNotExist";
        when(personDAO.getPersons()).thenReturn(personList);
        Person actualPerson = personService.getPerson(name);
        assertNull(actualPerson);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPersonEmailIsNull() {
        personService.getPerson(null);
    }

    @Test
    public void addPersonPositiveResult() {
        int expectedResult = 1;
        when(personDAO.addPerson(expectedPerson)).thenReturn(1);
        assertEquals(expectedResult, personService.addPerson(expectedPerson));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addPersonNullArgument() {
        personService.getPerson(null);
    }

    @Test
    public void updatePasswordPositiveResult() {
        int expectedResult = 1;
        when(personDAO.updatePassword(testEmail, testPassword)).thenReturn(1);
        assertEquals(expectedResult, personService.updatePassword(testEmail, testPassword));
    }

    @Test(expected = IllegalArgumentException.class)
    public void updatePasswordEmailIsNull() {
        personService.updatePassword(null, testPassword);
    }

    @Test
    public void addToBlackListPositiveResult() {
        int expectedResult = 1;
        when(personDAO.addToBlackList(testEmail)).thenReturn(1);
        assertEquals(expectedResult, personService.addToBlackList(testEmail));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addToBlackListEmailIsNull() {
        personService.addToBlackList(null);
    }

    @Test
    public void removeFromBlackList() {
        int expectedResult = 1;
        when(personDAO.removeFromBlackList(testEmail)).thenReturn(1);
        assertEquals(expectedResult, personService.removeFromBlackList(testEmail));
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeFromBlackListEmailIsNull() {
        personService.removeFromBlackList(null);
    }

    @Test
    public void giveAdminRightsPositiveResult() {
        int expectedResult = 1;
        when(personDAO.giveAdminRights(testEmail)).thenReturn(1);
        assertEquals(expectedResult, personService.giveAdminRights(testEmail));
    }

    @Test(expected = IllegalArgumentException.class)
    public void giveAdminRightsEmailIsNull() {
        personService.giveAdminRights(null);
    }

    @Test
    public void getIdByEmail(){
        Integer expectedId = 1;
        when(personDAO.getIdByEmail(testEmail)).thenReturn(expectedId);
        Integer actualId =personService.getIdByEmail(testEmail);
        assertEquals(expectedId, actualId);
    }
    @Test(expected = IllegalArgumentException.class)
    public void getIdByEmailNullEmail(){
        personService.getIdByEmail("");
    }
}