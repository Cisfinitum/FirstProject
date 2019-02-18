package com.epam.repository;

import com.epam.model.Reservation;
import com.epam.model.ReservationStatusEnum;
import lombok.SneakyThrows;
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
    private ReservationStatusEnum reservationStatusEnum = ReservationStatusEnum.PAID;
    private Integer testDiscountId = 1;
    private Integer testTotalPrice = 10_000;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @SneakyThrows
    @Test
    public void buildPerson() {
        Integer testId = 1;
        when(resultSet.getInt("id")).thenReturn(testId);
        when(resultSet.getInt("clientId")).thenReturn(testClientId);
        when(expectedReservation.getId()).thenReturn(testId);
        when(resultSet.getInt("tourOffer_id")).thenReturn(testTourOfferId);
        when(resultSet.getInt("numberOfPeople")).thenReturn(testNumberOfPeople);
        when(resultSet.getString("status")).thenReturn(reservationStatusEnum.getEnumStatus());
        when(resultSet.getInt("discount_id")).thenReturn(testDiscountId);
        when(resultSet.getInt("totalPrice")).thenReturn(testTotalPrice);
        Reservation actualReservation = reservationDAO.buildReservation(resultSet);
        assertEquals(expectedReservation.getId(), actualReservation.getId());
    }

    @SneakyThrows
    @Test(expected = SQLException.class)
    public void buildPersonThrowsException() {
        when(resultSet.getInt("id")).thenThrow(new SQLException());
        when(resultSet.getInt("clientId")).thenReturn(testClientId);
        when(resultSet.getInt("tourOfferId")).thenReturn(testTourOfferId);
        when(resultSet.getInt("numberOfPeople")).thenReturn(testNumberOfPeople);
        when(resultSet.getString("status")).thenReturn(reservationStatusEnum.getEnumStatus());
        when(resultSet.getInt("discountId")).thenReturn(testDiscountId);
        when(resultSet.getInt("totalPrice")).thenReturn(testTotalPrice);
        reservationDAO.buildReservation(resultSet);
    }

}