package com.epam.repository;

import com.epam.model.Hotel;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.ResultSet;
import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class HotelDAOTest {

    @Mock
    private ResultSet resultSet;

    @Mock
    private Hotel expectedHotel;

    @InjectMocks
    private HotelDAO hotelDAO;

    private Integer testId = 1;
    private String testHotelName = "Luxury Hotel";
    private String testHotelCity = "Moscow";
    private String testHotelCountry = "Russia";
    private Integer testHotelStars = 5;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @SneakyThrows(SQLException.class)
    public void buildHotel(){
        when(resultSet.getInt("id")).thenReturn(testId);
        when(resultSet.getString("name")).thenReturn(testHotelName);
        when(resultSet.getString("city")).thenReturn(testHotelCity);
        when(resultSet.getString("country")).thenReturn(testHotelCountry);
        when(resultSet.getInt("numberOfStars")).thenReturn(testHotelStars);
        when(expectedHotel.getName()).thenReturn(testHotelName);
        Hotel actualHotel = hotelDAO.buildHotel(resultSet);
        assertEquals(expectedHotel.getName(), actualHotel.getName());
    }

    @Test(expected = SQLException.class)
    @SneakyThrows(SQLException.class)
    public void buildHotelSQLException(){
        when(resultSet.getInt("id")).thenThrow(new SQLException());
        Hotel actualHotel = hotelDAO.buildHotel(resultSet);
    }
}