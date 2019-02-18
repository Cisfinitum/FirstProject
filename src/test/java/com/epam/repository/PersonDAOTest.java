package com.epam.repository;

import com.epam.model.Person;

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

    private String testEmail = "user";
    private String testPassword = "123";
    private String testPersonRoleEnum = "ADMIN";

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

}