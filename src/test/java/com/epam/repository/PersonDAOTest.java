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
        int testId = 1;
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
        int expectedResult = 1;
        String sql = "UPDATE person SET role = BLOCKED WHERE email = ?";
        when(jdbcTemplate.update(sql, testEmail)).thenReturn(1);
        assertEquals(expectedResult, personDAO.addToBlackList(testEmail));
    }

    @Test
    public void addToBlackListEmailIsEmpty() {
        int expectedResult = -1;
        String emptyEmail = "";
        String sql = "UPDATE person SET role = BLOCKED WHERE email = ?";
        when(jdbcTemplate.update(sql, emptyEmail)).thenReturn(1);
        assertEquals(expectedResult, personDAO.addToBlackList(emptyEmail));
    }

    @Test
    public void removeFromBlackListPositiveResult() {
        int expectedResult = 1;
        String sql = "UPDATE person SET role = USER WHERE email = ?";
        when(jdbcTemplate.update(sql, testEmail)).thenReturn(1);
        assertEquals(expectedResult, personDAO.removeFromBlackList(testEmail));
    }

    @Test
    public void removeFromBlackListEmailIsEmpty() {
        int expectedResult = -1;
        String emptyEmail = "";
        String sql = "UPDATE person SET role = USER WHERE email = ?";
        when(jdbcTemplate.update(sql, emptyEmail)).thenReturn(1);
        assertEquals(expectedResult, personDAO.removeFromBlackList(emptyEmail));
    }

    @Test
    public void giveAdminRightsPositiveResult() {
        int expectedResult = 1;
        String sql = "UPDATE person SET role = ADMIN WHERE email = ?";
        when(jdbcTemplate.update(sql, testEmail)).thenReturn(1);
        assertEquals(expectedResult, personDAO.giveAdminRights(testEmail));
    }

    @Test
    public void giveAdminRightsEmptyEmail() {
        int expectedResult = -1;
        String emptyEmail = "";
        String sql = "UPDATE person SET role = ADMIN WHERE email = ?";
        when(jdbcTemplate.update(sql, emptyEmail)).thenReturn(1);
        assertEquals(expectedResult, personDAO.giveAdminRights(emptyEmail));
    }

    @Test
    public void updatePasswordPositiveResult() {
        int expectedResult = 1;
        String sql = "UPDATE person SET password = ? WHERE email = ?";
        when(jdbcTemplate.update(sql, testPassword, testEmail)).thenReturn(1);
        assertEquals(expectedResult, personDAO.updatePassword(testEmail, testPassword));
    }

    @Test
    public void updatePasswordEmptyEmail() {
        int expectedResult = -1;
        String emptyEmail = "";
        String sql = "UPDATE person SET password = ? WHERE email = ?";
        when(jdbcTemplate.update(sql, testPassword, emptyEmail)).thenReturn(-1);
        assertEquals(expectedResult, personDAO.updatePassword(emptyEmail, testPassword));
    }
}