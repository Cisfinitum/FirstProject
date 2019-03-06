package com.epam.service;


import com.epam.exception.InvalidDataBaseAffectedException;
import com.epam.model.Person;
import com.epam.model.PersonRoleEnum;
import com.epam.model.Reservation;
import com.epam.repository.PersonDAO;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

public class PersonServiceTest {
    @Mock
    private PersonDAO personDAO;

    @Mock
    private Reservation reservation;

    @Mock
    private BCryptPasswordEncoder testPasswordEncoder;


    private static final int EXPECTED_RESULT = 1;
    private static final int UNEXPECTED_RESULT = 10;
    private static final String NULL_ARGUMENT = null;
    private List<Person> personList;
    private PersonService personService;
    private Person expectedPerson;
    private String testEmail;
    private String testPassword;
    private Integer testId;
    private String testPhoneNumber = "8999999999";
    private String testFirstName = "Example";
    private String testLastName = "Example";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testId = 1;
        testEmail = "user";
        testPassword = "1111";
        expectedPerson = new Person(testId, testEmail, testPassword, PersonRoleEnum.valueOf("ADMIN"), testPhoneNumber, testFirstName, testLastName);
        personService = new PersonService(personDAO, testPasswordEncoder);
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

//    @Test
//    public void getPersonNotFound() {
//        String name = "isNotExist";
//        when(personDAO.getPersons()).thenReturn(personList);
//        Person actualPerson = personService.getPerson(name);
//        assertNull(actualPerson);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void getPersonEmailIsNull() {
//        personService.getPerson(null);
//    }
//
//    @Test
//    public void addPersonPositiveResult() {
//        int expectedResult = 1;
//        when(personDAO.addPerson(expectedPerson)).thenReturn(1);
//        assertEquals(expectedResult, personService.addPerson(expectedPerson));
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void addPersonNullArgument() {
//        personService.getPerson(null);
//    }

    @Test
    public void updatePasswordPositiveResult() {
        when(personDAO.updatePassword(testEmail, testPassword)).thenReturn(EXPECTED_RESULT);
        assertTrue(personService.updatePassword(testEmail, testPassword));
    }

    @Test
    public void updatePasswordNegativeResult() {
        when(personDAO.updatePassword(testEmail, testPassword)).thenReturn(-1);
        assertFalse(personService.updatePassword(testEmail, testPassword));
    }

    @Test(expected = InvalidDataBaseAffectedException.class)
    public void updatePasswordMoreThanOneRow() {
        when(personDAO.updatePassword(testEmail, testPassword)).thenReturn(UNEXPECTED_RESULT);
        personService.updatePassword(testEmail, testPassword);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updatePasswordEmailIsNull() {
        personService.updatePassword(NULL_ARGUMENT, testPassword);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updatePasswordPassIsNull() {
        personService.updatePassword(testEmail, NULL_ARGUMENT);
    }

    @Test
    public void updatePasswordByIdPositiveResult() {
        when(personDAO.updatePasswordById(testId, testPassword)).thenReturn(EXPECTED_RESULT);
        assertTrue(personService.updatePasswordById(testId, testPassword));
    }

    @Test
    public void updatePasswordByIdNegativeResult() {
        when(personDAO.updatePasswordById(testId, testPassword)).thenReturn(-1);
        assertFalse(personService.updatePasswordById(testId, testPassword));
    }

    @Test(expected = InvalidDataBaseAffectedException.class)
    public void updatePasswordByIdMoreThanOneRow() {
        when(personDAO.updatePasswordById(testId, testPassword)).thenReturn(UNEXPECTED_RESULT);
        personService.updatePasswordById(testId, testPassword);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updatePasswordByIdsNull() {
        personService.updatePasswordById(null, testPassword);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updatePasswordByPasswordsNull() {
        personService.updatePasswordById(testId, NULL_ARGUMENT);
    }
//    @Test
//    public void addToBlackListPositiveResult() {
//        int expectedResult = 1;
//        when(personDAO.addToBlackList(testId)).thenReturn(1);
//        assertEquals(expectedResult, personService.addToBlackList(testId));
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void addToBlackListEmailIsNull() {
//        personService.addToBlackList(null);
//    }
//
//    @Test
//    public void removeFromBlackList() {
//        int expectedResult = 1;
//        when(personDAO.removeFromBlackList(testId)).thenReturn(1);
//        assertEquals(expectedResult, personService.removeFromBlackList(testId));
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void removeFromBlackListEmailIsNull() {
//        personService.removeFromBlackList(null);
//    }
//
//    @Test
//    public void giveAdminRightsPositiveResult() {
//        int expectedResult = 1;
//        when(personDAO.giveAdminRights(testId)).thenReturn(1);
//        assertEquals(expectedResult, personService.giveAdminRights(testId));
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void giveAdminRightsEmailIsNull() {
//        personService.giveAdminRights(null);
//    }
//
//    @Test
//    public void getIdByEmail() {
//        Integer expectedId = 1;
//        when(personDAO.getIdByEmail(testEmail)).thenReturn(expectedId);
//        Integer actualId = personService.getIdByEmail(testEmail);
//        assertEquals(expectedId, actualId);
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void getIdByEmailNullEmail() {
//        personService.getIdByEmail("");
//    }

    @Test
    public void updatePhoneNumberPositiveResult() {
        when(personDAO.updatePhoneNumberById(testId, testPhoneNumber)).thenReturn(EXPECTED_RESULT);
        assertTrue(personService.updatePhoneNumberById(testId, testPhoneNumber));
    }

    @Test(expected = IllegalArgumentException.class)
    public void updatePhoneNumberNullArgument() {
        personService.updatePhoneNumberById(testId, NULL_ARGUMENT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updatePhoneNumberIdNullArgument() {
        personService.updatePhoneNumberById(null, testPhoneNumber);
    }

    @Test(expected = InvalidDataBaseAffectedException.class)
    public void updatePhoneNumberDataBaseException() {
        when(personDAO.updatePhoneNumberById(testId, testPhoneNumber)).thenReturn(UNEXPECTED_RESULT);
        personService.updatePhoneNumberById(testId, testPhoneNumber);
    }

    @Test
    public void updatePhoneNumberInvalidId() {
        when(personDAO.updatePhoneNumberById(testId, testPassword)).thenReturn(0);
        assertFalse(personService.updatePhoneNumberById(testId, testPassword));
    }

    @Test
    public void updateFirstNamePositiveResult() {
        when(personDAO.updateFirstNameById(testId, testFirstName)).thenReturn(EXPECTED_RESULT);
        assertTrue(personService.updateFirstNameById(testId, testFirstName));
    }

    @Test
    public void updateFirstNameNegativeResult() {
        when(personDAO.updateFirstNameById(testId, testFirstName)).thenReturn(0);
        assertFalse(personService.updateFirstNameById(testId, testFirstName));
    }

    @Test(expected = InvalidDataBaseAffectedException.class)
    public void updateFirstNamePositiveResultInvalid() {
        when(personDAO.updateFirstNameById(testId, testFirstName)).thenReturn(UNEXPECTED_RESULT);
        personService.updateFirstNameById(testId, testFirstName);
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateFirstNameNullArgument() {
        personService.updateFirstNameById(testId, NULL_ARGUMENT);
    }

    @Test
    public void updateLastNamePositiveResult() {
        when(personDAO.updateLastNameById(testId, testLastName)).thenReturn(EXPECTED_RESULT);
        assertTrue( personService.updateLastNameById(testId, testLastName));
    }

    @Test
    public void updateLastNameNegativeResult() {
        when(personDAO.updateLastNameById(testId, testLastName)).thenReturn(0);
        assertFalse( personService.updateLastNameById(testId, testLastName));
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateLastNameNullArgument() {
        personService.updateLastNameById(testId, NULL_ARGUMENT);
    }
    @Test(expected = InvalidDataBaseAffectedException.class)
    public void updateLastNameDataBaseException() {
        when(personDAO.updateLastNameById(testId, testLastName)).thenReturn(UNEXPECTED_RESULT);
        personService.updateLastNameById(testId, testLastName);
    }

    @Test
    public void updateEmailPositiveResult() {
        when(personDAO.updateEmailById(testId, testEmail)).thenReturn(EXPECTED_RESULT);
        assertTrue(personService.updateEmailById(testId, testEmail));
    }

    @Test
    public void updateEmailNegativeResult() {
        when(personDAO.updateEmailById(testId, testEmail)).thenReturn(0);
        assertFalse(personService.updateEmailById(testId, testEmail));
    }

    @Test(expected = IllegalArgumentException.class)
    public void updateEmailNullArgument() {
        personService.updateEmailById(testId, NULL_ARGUMENT);
    }

    @Test(expected = InvalidDataBaseAffectedException.class)
    public void updateEmailDBException() {
        when(personDAO.updateEmailById(testId, testEmail)).thenReturn(UNEXPECTED_RESULT);
        personService.updateEmailById(testId, testEmail);
    }

    @Test
    public void mapOfUsersInformation() {
        when(reservation.getClientId()).thenReturn(1);
        when(personDAO.getPersonById(1)).thenReturn(expectedPerson);
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation);

        Map<Integer, String> actual = personService.mapOfUsersInformation(reservations);

        assertEquals(expectedPerson.toString(), actual.get(1));
    }

    @Test
    public void mapOfUsersInformationEmptyList() {
        List<Reservation> reservations = new ArrayList<>();

        Map<Integer, String> actual = personService.mapOfUsersInformation(reservations);

        assertTrue(actual.isEmpty());
    }

    @Test
    public void listOfUsers() {
        Integer page = 1;
        Integer rowNum = 1;
        when(personDAO.listOfUsers(page, rowNum)).thenReturn(personList);

        List<Person> actual = personService.listOfUsers(page, rowNum);

        assertEquals(personList, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void listOfUserPageRowNull() {

        List<Person> actual = personService.listOfUsers(null, null);

        assertEquals(personList, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void listOfUserPageOrRowNegative() {
        Integer rowNum = -1;
        Integer page = -1;

        List<Person> actual = personService.listOfUsers(page, rowNum);

        assertEquals(personList, actual);
    }
}