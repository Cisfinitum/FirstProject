package com.epam.repository;

import com.epam.model.Person;
import com.epam.model.PersonRoleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.omg.CORBA.PERSIST_STORE;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PersonDAOTest {
    @Mock
    private ResultSet resultSet;
    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private PersonDAO personDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        personDAO = new PersonDAO(namedParameterJdbcTemplate);
    }

    @Test
    void buildPersonPositiveCheck() throws SQLException {
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
    void buildPersonThrowsException() throws SQLException {
        String testUserName = "user";
        String testPassword = "123";
        String testPersonRoleEnum = "ADMIN";
        when(resultSet.getInt("id")).thenThrow(new SQLException());
        when(resultSet.getString("nickname")).thenReturn(testUserName);
        when(resultSet.getString("password")).thenReturn(testPassword);
        when(resultSet.getString("role")).thenReturn(testPersonRoleEnum);
        try {
            Person actualPerson = personDAO.buildPerson(resultSet);
            fail();
        } catch (SQLException thrown) {
            assertNotNull(thrown);
        }
    }
}