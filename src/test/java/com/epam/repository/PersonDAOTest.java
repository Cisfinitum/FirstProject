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
    private String testEmail = "user@email.com";
    private String testPassword = "Goodpassword1";
    private String testPersonRoleEnum = "USER";
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
        testPerson = new Person(testEmail, testPassword, PersonRoleEnum.valueOf(testPersonRoleEnum));
    }

    @Test
    public void buildPersonPositiveCheck() throws SQLException {
        int testId = EXPECTED_RESULT;
        when(resultSet.getInt("id")).thenReturn(testId);
        when(resultSet.getString("email")).thenReturn(testEmail);
        when(resultSet.getString("password")).thenReturn(testPassword);
        when(resultSet.getString("role")).thenReturn(testPersonRoleEnum);
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

//    smth goes wrong here (!)
//    @Test
//    public void addPersonPositiveResult() {
//        int expectedResult = 1;
//        when(personDAO.doesEmailExist(testEmail)).thenReturn(true);
//        String sql = "INSERT INTO person (email, password, role) VALUES (?, ?, ?)";
//        when(jdbcTemplate.update(sql, testEmail, testPassword, testPersonRoleEnum)).thenReturn(1);
//        assertEquals(expectedResult, personDAO.addPerson(testPerson));
//    }

    @Test
    public void addToBlackListPositiveResult() {
        String sql = "UPDATE person SET role = BLOCKED WHERE email = ?";
        when(jdbcTemplate.update(sql, testEmail)).thenReturn(EXPECTED_RESULT);
        assertEquals(EXPECTED_RESULT, personDAO.addToBlackList(testEmail));
    }

    @Test
    public void addToBlackListEmailIsEmpty() {
        String sql = "UPDATE person SET role = BLOCKED WHERE email = ?";
        when(jdbcTemplate.update(sql, UNEXPECTED_RESULT)).thenReturn(UNEXPECTED_RESULT);
        assertEquals(UNEXPECTED_RESULT, personDAO.addToBlackList(EMPTY_EMAIL));
    }

    @Test
    public void removeFromBlackListPositiveResult() {
        String sql = "UPDATE person SET role = USER WHERE email = ?";
        when(jdbcTemplate.update(sql, testEmail)).thenReturn(EXPECTED_RESULT);
        assertEquals(EXPECTED_RESULT, personDAO.removeFromBlackList(testEmail));
    }

    @Test
    public void removeFromBlackListEmailIsEmpty() {
        String sql = "UPDATE person SET role = USER WHERE email = ?";
        when(jdbcTemplate.update(sql, EMPTY_EMAIL)).thenReturn(UNEXPECTED_RESULT);
        assertEquals(UNEXPECTED_RESULT, personDAO.removeFromBlackList(EMPTY_EMAIL));
    }

    @Test
    public void giveAdminRightsPositiveResult() {
        String sql = "UPDATE person SET role = ADMIN WHERE email = ?";
        when(jdbcTemplate.update(sql, testEmail)).thenReturn(EXPECTED_RESULT);
        assertEquals(EXPECTED_RESULT, personDAO.giveAdminRights(testEmail));
    }

    @Test
    public void giveAdminRightsEmptyEmail() {
        String sql = "UPDATE person SET role = ADMIN WHERE email = ?";
        when(jdbcTemplate.update(sql, EMPTY_EMAIL)).thenReturn(UNEXPECTED_RESULT);
        assertEquals(UNEXPECTED_RESULT, personDAO.giveAdminRights(EMPTY_EMAIL));
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
}