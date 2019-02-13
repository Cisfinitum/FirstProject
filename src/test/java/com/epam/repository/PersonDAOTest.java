package com.epam.repository;

import com.epam.model.Person;
import com.epam.model.PersonRoleEnum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class PersonDAOTest {
    @Mock
    private ResultSet resultSet;
    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private PersonDAO personDAO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        personDAO = new PersonDAO(namedParameterJdbcTemplate);
    }

    @Test
    public void buildPersonPositiveCheck() throws SQLException {
        int testId = 1;
        String testUserName = "user";
        String testPassword = "123";
        String testPersonRoleEnum = "ADMIN";
        when(resultSet.getInt("id")).thenReturn(testId);
        when(resultSet.getString("nickname")).thenReturn(testUserName);
        when(resultSet.getString("password")).thenReturn(testPassword);
        when(resultSet.getString("role")).thenReturn(testPersonRoleEnum);
        Person expectedPerson = new Person(testId, testUserName, testPassword, PersonRoleEnum.ADMIN);
        Person actualPerson = personDAO.buildPerson(resultSet);
        assertEquals(expectedPerson.getName(), actualPerson.getName());
    }

    @Test
    public void buildPersonThrowsException() throws SQLException {
        String testUserName = "user";
        String testPassword = "123";
        String testPersonRoleEnum = "ADMIN";
        when(resultSet.getInt("id")).thenThrow(new SQLException());
        when(resultSet.getString("nickname")).thenReturn(testUserName);
        when(resultSet.getString("password")).thenReturn(testPassword);
        when(resultSet.getString("role")).thenReturn(testPersonRoleEnum);
        try {
            personDAO.buildPerson(resultSet);
            fail();
        } catch (SQLException thrown) {
            assertNotNull(thrown);
        }
    }
}