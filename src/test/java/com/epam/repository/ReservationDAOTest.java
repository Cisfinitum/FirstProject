package com.epam.repository;

import com.epam.model.Reservation;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ReservationDAOTest {
    @InjectMocks
    ReservationDAO reservationDAO;
    @Mock
    private ResultSet resultSet;
    @Mock
    private Reservation expectedReservation;

    private Integer testClientId = 1;
    private Integer testTourOfferId = 1;
    private Integer testNumberOfPeople = 1;
    private String testStatus = "paid";
    private Integer testDiscountId = 1;
    private Integer testTotalPrice = 10_000;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void buildPersonPositiveCheck() throws SQLException {
        Integer testId = 1;
        when(resultSet.getInt("id")).thenReturn(testId);
        when(resultSet.getInt("client_id")).thenReturn(testClientId);
        when(resultSet.getInt("tourOffer_id")).thenReturn(testTourOfferId);
        when(resultSet.getInt("numberOfPeople")).thenReturn(testNumberOfPeople);
        when(resultSet.getString("status")).thenReturn(testStatus);
        when(resultSet.getInt("discount_id")).thenReturn(testDiscountId);
        when(resultSet.getInt("totalPrice")).thenReturn(testTotalPrice);
        when(expectedReservation.getId()).thenReturn(testId);
        Reservation actualReservation = reservationDAO.buildReservation(resultSet);
        assertEquals(expectedReservation.getId(), actualReservation.getId());
    }

    @Test(expected = SQLException.class)
    public void buildPersonThrowsException() throws SQLException {
        when(resultSet.getInt("id")).thenThrow(new SQLException());
        when(resultSet.getInt("client_id")).thenReturn(testClientId);
        when(resultSet.getInt("tourOffer_id")).thenReturn(testTourOfferId);
        when(resultSet.getInt("numberOfPeople")).thenReturn(testNumberOfPeople);
        when(resultSet.getString("status")).thenReturn(testStatus);
        when(resultSet.getInt("discount_id")).thenReturn(testDiscountId);
        when(resultSet.getInt("totalPrice")).thenReturn(testTotalPrice);
        reservationDAO.buildReservation(resultSet);
    }
}