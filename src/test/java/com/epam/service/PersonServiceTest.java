//package com.epam.service;
//
//
//import com.epam.model.Person;
//import com.epam.model.PersonRoleEnum;
//import com.epam.repository.PersonDAO;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNull;
//import static org.mockito.Mockito.when;
//
//public class PersonServiceTest {
//    @Mock
//    private PersonDAO personDAO;
//
//
//    public static final int EXPECTED_RESULT = 1;
//    public static final String NULL_ARGUMENT = null;
//    private List<Person> personList;
//    private PersonService personService;
//    private Person expectedPerson;
//    private String testEmail;
//    private String testPassword;
//    private Integer testId;
//    private String testPhoneNumber = "8999999999";
//    private String testFirstName = "Example";
//    private String testLastName = "Example";
//
//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);
//        testId = 1;
//        testEmail = "user";
//        testPassword = "1111";
//        expectedPerson = new Person(testId, testEmail, testPassword, PersonRoleEnum.valueOf("ADMIN"), testPhoneNumber, testFirstName, testLastName);
//        personService = new PersonService(personDAO);
//        personList = new ArrayList<>();
//        personList.add(expectedPerson);
//    }
//
//    @Test
//    public void getPersonPositiveResult() {
//        String email = "user";
//        when(personDAO.getPersons()).thenReturn(personList);
//        Person actualPerson = personService.getPerson(email);
//        assertEquals(expectedPerson, actualPerson);
//    }
//
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
//
//    @Test
//    public void updatePasswordPositiveResult() {
//        int expectedResult = 1;
//        when(personDAO.updatePassword(testEmail, testPassword)).thenReturn(1);
//        assertEquals(expectedResult, personService.updatePassword(testEmail, testPassword));
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void updatePasswordEmailIsNull() {
//        personService.updatePassword(null, testPassword);
//    }
//
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
//
//    @Test
//    public void updatePhoneNumberPositiveResult() {
//        when(personDAO.updatePhoneNumberById(testId, testPhoneNumber)).thenReturn(EXPECTED_RESULT);
//        assertEquals(EXPECTED_RESULT, personService.updatePhoneNumberById(testId, testPhoneNumber));
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void updatePhoneNumberNullArgument() {
//        personService.updatePhoneNumberById(testId, NULL_ARGUMENT);
//    }
//
//    @Test
//    public void updateFirstNamePositiveResult() {
//        when(personDAO.updateFirstNameById(testId, testFirstName)).thenReturn(EXPECTED_RESULT);
//        assertEquals(EXPECTED_RESULT, personService.updateFirstNameById(testId, testFirstName));
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void updateFirstNameNullArgument() { personService.updateFirstNameById(testId, NULL_ARGUMENT);
//    }
//
//    @Test
//    public void updateLastNamePositiveResult() {
//        when(personDAO.updateLastNameById(testId, testLastName)).thenReturn(EXPECTED_RESULT);
//        assertEquals(EXPECTED_RESULT, personService.updateLastNameById(testId, testLastName));
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void updateLastNameNullArgument() { personService.updateLastNameById(testId, NULL_ARGUMENT);
//    }
//
//    @Test
//    public void updateEmailPositiveResult() {
//        when(personDAO.updateEmailById(testId, testEmail)).thenReturn(EXPECTED_RESULT);
//        assertEquals(EXPECTED_RESULT, personService.updateEmailById(testId, testEmail));
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void updateEmailNullArgument() { personService.updateEmailById(testId, NULL_ARGUMENT);
//    }
//}