package com.epam.repository;

import com.epam.model.Hotel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class HotelDAOTest {

    private Integer testId = 1;
    private String testHotelName = "Luxury Hotel";
    private String testHotelCity = "Moscow";
    private String testHotelCountry = "Russia";
    private Integer testHotelStars = 5;

    @Mock
    private HotelDAO hotelDAO;

    @Mock
    private ResultSet resultSet;

    @Mock
    private JdbcTemplate jdbcTemplate;

    private Hotel h;

    @Before
    public void setUp() throws SQLException {
        MockitoAnnotations.initMocks(this);
        when(resultSet.getInt("id")).thenReturn(testId);
    }


    @Test
    public void testCreateHotel() throws SQLException {
        hotelDAO = new HotelDAO(jdbcTemplate);
        h = hotelDAO.buildHotel(resultSet);
        assertNotNull(h.getId());
    }


}
