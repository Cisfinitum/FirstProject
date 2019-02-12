package com.epam.service;

import com.epam.model.Person;
import com.epam.model.PersonRoleEnum;
import com.epam.repository.PersonDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

class PersonServiceTest {

    @Mock
    private PersonDAO personDAO;

    private List<Person> personList;
    private PersonService personService;
    private Person expectedPerson;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        expectedPerson= new Person(1, "user", "1111", PersonRoleEnum.valueOf("ADMIN"));
        personService = new PersonService(personDAO);
        personList = new ArrayList<>();
        personList.add(expectedPerson);
    }

    @Test
    void getPersonPositiveResult() {
        String name = "user";
        when(personDAO.getPersons()).thenReturn(personList);

        Person actualPerson = personService.getPerson(name);

        assertEquals(expectedPerson, actualPerson);
    }
    @Test
    void getPersonNotFound() {
        String name = "tmp";
        when(personDAO.getPersons()).thenReturn(personList);

        Person actualPerson = personService.getPerson(name);

        assertNull(actualPerson);
    }


}