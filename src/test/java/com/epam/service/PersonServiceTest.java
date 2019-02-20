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

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        expectedPerson = new Person(1, "user", "1111", PersonRoleEnum.valueOf("ADMIN"));
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
        String name = "tmp";
        when(personDAO.getPersons()).thenReturn(personList);
        Person actualPerson = personService.getPerson(name);
        assertNull(actualPerson);
    }
}