package com.epam.repository;

import com.epam.model.Reservation;
import com.epam.model.ReservationArchiveStatusEnum;
import com.epam.model.ReservationStatusEnum;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ReservationDAOTest {
    @InjectMocks
    ReservationDAO reservationDAO;
    @Mock
    private ResultSet resultSet;
    @Mock
    private Reservation expectedReservation;
    @Mock
    private JdbcTemplate jdbcTemplate;

    private ReservationArchiveStatusEnum archiveStatusEnum = ReservationArchiveStatusEnum.NEW;
    private ReservationStatusEnum reservationStatusEnum = ReservationStatusEnum.PAID;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(reservationDAO, "tableName", "reservation");
        ReflectionTestUtils.setField(reservationDAO, "id", "id");
        ReflectionTestUtils.setField(reservationDAO, "clientId", "client_id");
        ReflectionTestUtils.setField(reservationDAO, "tourOfferId", "tour_offer_id");
        ReflectionTestUtils.setField(reservationDAO, "numberOfPeople", "number_of_people");
        ReflectionTestUtils.setField(reservationDAO, "status", "status");
        ReflectionTestUtils.setField(reservationDAO, "archiveStatus", "archive_status");
        ReflectionTestUtils.setField(reservationDAO, "discount", "discount");
        ReflectionTestUtils.setField(reservationDAO, "totalPrice", "total_price");
    }

    @SneakyThrows
    @Test
    public void buildPerson() {
        Integer testId = 1;
        when(resultSet.getInt("id")).thenReturn(testId);
        Integer testClientId = 1;
        when(resultSet.getInt("client_id")).thenReturn(testClientId);
        when(expectedReservation.getId()).thenReturn(testId);
        Integer testTourOfferId = 1;
        when(resultSet.getInt("tour_offer_id")).thenReturn(testTourOfferId);
        Integer testNumberOfPeople = 1;
        when(resultSet.getInt("number_of_people")).thenReturn(testNumberOfPeople);
        when(resultSet.getString("archive_status")).thenReturn(archiveStatusEnum.getEnumArchiveStatus());
        when(resultSet.getString("status")).thenReturn(reservationStatusEnum.getEnumStatus());
        Integer testDiscount = 10;
        when(resultSet.getInt("discount_id")).thenReturn(testDiscount);
        Integer testTotalPrice = 10_000;
        when(resultSet.getInt("total_price")).thenReturn(testTotalPrice);
        Reservation actualReservation = reservationDAO.buildReservation(resultSet);
        assertEquals(expectedReservation.getId(), actualReservation.getId());
    }

    @SneakyThrows
    @Test(expected = SQLException.class)
    public void buildPersonThrowsException() {
        when(resultSet.getInt("id")).thenThrow(new SQLException());
        reservationDAO.buildReservation(resultSet);
    }
//
//    @Test
//    public void listReservations(){
//        List<Reservation> reservations = new ArrayList<Reservation>();
//        reservations.add(expectedReservation);
//        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(reservations);
//        verify(jdbcTemplate).query(anyString(), any(Object[].class), any(RowMapper.class));
//    }
}