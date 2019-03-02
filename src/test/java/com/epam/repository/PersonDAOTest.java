package com.epam.repository;

import com.epam.model.Person;

import com.epam.model.PersonRoleEnum;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.ResultSet;
import java.sql.SQLException;


import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class PersonDAOTest {

    public static final int EXPECTED_RESULT = 1;
    public static final int UNEXPECTED_RESULT = -1;
    public static final String EMPTY_EMAIL = "";
    public static final String EMPTY_PHONE_NUMBER = "";
    public static final String EMPTY_NAME = "";
    private Integer testId = 1;
    private String testEmail = "user@email.com";
    private String testPassword = "Goodpassword1";
    private String testPersonRoleEnum = "USER";
    private String testPhoneNumber = "8999999999";
    private String testFirstName = "Example";
    private String testLastName = "Example";
    private Person testPerson;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private ResultSet resultSet;

    @Mock
    private Person expectedPerson;

    @InjectMocks
    private PersonDAO personDAO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testPerson = new Person(testId, testEmail, testPassword, PersonRoleEnum.valueOf(testPersonRoleEnum), testPhoneNumber, testFirstName, testLastName);
    }

    @Test
    public void buildPersonPositiveCheck() throws SQLException {
        int testId = EXPECTED_RESULT;
        when(resultSet.getInt("id")).thenReturn(testId);
        when(resultSet.getString("email")).thenReturn(testEmail);
        when(resultSet.getString("password")).thenReturn(testPassword);
        when(resultSet.getString("role")).thenReturn(testPersonRoleEnum);
        when(resultSet.getString("phoneNumber")).thenReturn(testPhoneNumber);
        when(resultSet.getString("firstName")).thenReturn(testFirstName);
        when(resultSet.getString("lastName")).thenReturn(testLastName);
        when(expectedPerson.getEmail()).thenReturn(testEmail);
        Person actualPerson = personDAO.buildPerson(resultSet);
        assertEquals(expectedPerson.getEmail(), actualPerson.getEmail());
    }

    @Test(expected = SQLException.class)
    public void buildPersonThrowsException() throws SQLException {
        when(resultSet.getInt("id")).thenThrow(new SQLException());
        when(resultSet.getString("email")).thenReturn(testEmail);
        when(resultSet.getString("password")).thenReturn(testPassword);
        when(resultSet.getString("role")).thenReturn(testPersonRoleEnum);
        when(resultSet.getString("phoneNumber")).thenReturn(testPhoneNumber);
        when(resultSet.getString("firstName")).thenReturn(testFirstName);
        when(resultSet.getString("lastName")).thenReturn(testLastName);
        personDAO.buildPerson(resultSet);
    }

    @Test
    public void getEmailPositiveResult() throws SQLException {
        when(resultSet.getString(("email"))).thenReturn(testEmail);
        String actualEmail = personDAO.getEmail(resultSet);
        assertEquals(testEmail, actualEmail);
    }

    @Test(expected = SQLException.class)
    public void getEmailNegativeResult() throws SQLException {
        when(resultSet.getString(("email"))).thenThrow(new SQLException());
        personDAO.getEmail(resultSet);
    }

    @Test
    public void addToBlackListPositiveResult() {
        String sql = "UPDATE person SET role = 'BLOCKED' WHERE id = ?";
        when(jdbcTemplate.update(sql, testId)).thenReturn(EXPECTED_RESULT);
        assertEquals(EXPECTED_RESULT, personDAO.addToBlackList(testId));
    }

    @Test
    public void addToBlackListIncorrectId() {
        String sql = "UPDATE person SET role = 'BLOCKED' WHERE id = ?";
        when(jdbcTemplate.update(sql, UNEXPECTED_RESULT)).thenReturn(UNEXPECTED_RESULT);
        assertEquals(UNEXPECTED_RESULT, personDAO.addToBlackList(UNEXPECTED_RESULT));
    }

    @Test
    public void removeFromBlackListPositiveResult() {
        String sql = "UPDATE person SET role = 'USER' WHERE id = ?";
        when(jdbcTemplate.update(sql, testId)).thenReturn(EXPECTED_RESULT);
        assertEquals(EXPECTED_RESULT, personDAO.removeFromBlackList(testId));
    }

    @Test
    public void removeFromBlackListEmailIsEmpty() {
        String sql = "UPDATE person SET role = 'USER' WHERE id = ?";
        when(jdbcTemplate.update(sql, UNEXPECTED_RESULT)).thenReturn(UNEXPECTED_RESULT);
        assertEquals(UNEXPECTED_RESULT, personDAO.removeFromBlackList(UNEXPECTED_RESULT));
    }

    @Test
    public void giveAdminRightsPositiveResult() {
        String sql = "UPDATE person SET role = 'ADMIN' WHERE id = ?";
        when(jdbcTemplate.update(sql, testId)).thenReturn(EXPECTED_RESULT);
        assertEquals(EXPECTED_RESULT, personDAO.giveAdminRights(testId));
    }

    @Test
    public void giveAdminRightsEmptyEmail() {
        String sql = "UPDATE person SET role = ADMIN WHERE id = ?";
        when(jdbcTemplate.update(sql, UNEXPECTED_RESULT)).thenReturn(UNEXPECTED_RESULT);
        assertEquals(UNEXPECTED_RESULT, personDAO.giveAdminRights(UNEXPECTED_RESULT));
    }

    @Test
    public void updatePasswordPositiveResult() {
        String sql = "UPDATE person SET password = ? WHERE email = ?";
        when(jdbcTemplate.update(sql, testPassword, testEmail)).thenReturn(EXPECTED_RESULT);
        assertEquals(EXPECTED_RESULT, personDAO.updatePassword(testEmail, testPassword));
    }

    @Test
    public void updatePasswordEmptyEmail() {
        String sql = "UPDATE person SET password = ? WHERE email = ?";
        when(jdbcTemplate.update(sql, testPassword, EMPTY_EMAIL)).thenReturn(UNEXPECTED_RESULT);
        assertEquals(UNEXPECTED_RESULT, personDAO.updatePassword(EMPTY_EMAIL, testPassword));
    }

    @Test
    public void getIdByEmailGetsNonExistEmail() {
        Integer expectedResult = -1;
        String nonExistEmail = "admin@email.com";
        String sql = "SELECT id FROM person WHERE email = " + "'" + nonExistEmail + "'";
        when(jdbcTemplate.queryForObject(sql, Integer.class)).thenReturn(-1);
        Integer actualResult = personDAO.getIdByEmail(nonExistEmail);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void updatePhoneNumberPositiveResult() {
        String sql = "UPDATE person SET phoneNumber = ? WHERE id = ?";
        when(jdbcTemplate.update(sql, testPhoneNumber, testId)).thenReturn(EXPECTED_RESULT);
        assertEquals(EXPECTED_RESULT, personDAO.updatePhoneNumberById(testId, testPhoneNumber));
    }

    @Test
    public void updatePhoneNumberEmptyInput() {
        assertEquals(UNEXPECTED_RESULT, personDAO.updatePhoneNumberById(testId, EMPTY_PHONE_NUMBER));
    }

    @Test
    public void updateFirstNamePositiveResult() {
        String sql = "UPDATE person SET firstName = ? WHERE id = ?";
        when(jdbcTemplate.update(sql, testFirstName, testId)).thenReturn(EXPECTED_RESULT);
        assertEquals(EXPECTED_RESULT, personDAO.updateFirstNameById(testId, testFirstName));
    }

    @Test
    public void updateFirstNameEmptyInput() {
        assertEquals(UNEXPECTED_RESULT, personDAO.updateFirstNameById(testId, EMPTY_NAME));
    }

    @Test
    public void updateLastNamePositiveResult() {
        String sql = "UPDATE person SET lastName = ? WHERE id = ?";
        when(jdbcTemplate.update(sql, testLastName, testId)).thenReturn(EXPECTED_RESULT);
        assertEquals(EXPECTED_RESULT, personDAO.updateLastNameById(testId, testLastName));
    }

    @Test
    public void updateLastNameEmptyInput() {
        assertEquals(UNEXPECTED_RESULT, personDAO.updateLastNameById(testId, EMPTY_NAME));
    }

    @Test
    public void updateEmailPositiveResult() {
        String sql = "UPDATE person SET email = ? WHERE id = ?";
        when(jdbcTemplate.update(sql, testEmail, testId)).thenReturn(EXPECTED_RESULT);
        assertEquals(EXPECTED_RESULT, personDAO.updateEmailById(testId, testEmail));
    }

    @Test
    public void updateEmailEmptyEmail() {
        assertEquals(UNEXPECTED_RESULT, personDAO.updateEmailById(testId, EMPTY_EMAIL));
    }
}