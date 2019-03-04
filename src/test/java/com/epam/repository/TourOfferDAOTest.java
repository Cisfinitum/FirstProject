package com.epam.repository;

import com.epam.model.TourOffer;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

public class TourOfferDAOTest {
    @Mock
    private ResultSet resultSet;
    @Mock
    private TourOffer expectedTour;
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private TourOfferDAO tourofferDAO;

    private String testtourType = "Active";
    private Date teststartDate = Date.valueOf("2018-02-19");
    private Date testendDate = Date.valueOf("2018-02-25");
    private Integer testpricePerUnit = 1500;
    private Integer testhotel_id = 1;
    private String testdescription = "Best tour";
    private Integer testdiscount_id = 1;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(tourofferDAO, "tableName", "tour_offer");
        ReflectionTestUtils.setField(tourofferDAO, "tourTypeName", "tour_type");
        ReflectionTestUtils.setField(tourofferDAO, "startDateName", "start_date");
        ReflectionTestUtils.setField(tourofferDAO, "endDateName", "end_date");
        ReflectionTestUtils.setField(tourofferDAO, "hotelIdName", "hotel_id");
        ReflectionTestUtils.setField(tourofferDAO, "pricePerUnitName", "price_per_unit");
        ReflectionTestUtils.setField(tourofferDAO, "descriptionName", "description");
        ReflectionTestUtils.setField(tourofferDAO, "discountIdName", "discount");
        ReflectionTestUtils.setField(tourofferDAO, "idName", "id");
        expectedTour =  new TourOffer(1, "Active", LocalDate.of(2018,2,19),  LocalDate.of(2018,2,25),
                1500, 1,"Best tour", 1);
    }

    @Test
    @SneakyThrows(SQLException.class)
    public void buildTourCheck(){
        Integer testtourID = 1;
        when(resultSet.getInt("id")).thenReturn(testtourID);
        when(resultSet.getString("tour_type")).thenReturn(testtourType);
        when(resultSet.getDate("start_date")).thenReturn(teststartDate);
        when(resultSet.getDate("end_date")).thenReturn(testendDate);
        when(resultSet.getInt("price_per_unit")).thenReturn(testpricePerUnit);
        when(resultSet.getInt("hotel_id")).thenReturn(testhotel_id);
        when(resultSet.getString("description")).thenReturn(testdescription);
        when(resultSet.getInt("discount")).thenReturn(testdiscount);
        TourOffer actualTourOffer = tourofferDAO.buildTour(resultSet);
        assertEquals(expectedTour, actualTourOffer);
    }

    @Test(expected = SQLException.class)
    @SneakyThrows(SQLException.class)
    public void buildTourThrowsSQLException(){
        when(resultSet.getInt("id")).thenThrow(new SQLException());
        when(resultSet.getString("tour_type")).thenReturn(testtourType);
        when(resultSet.getDate("start_date")).thenReturn(teststartDate);
        when(resultSet.getDate("end_date")).thenReturn(testendDate);
        when(resultSet.getInt("price_per_unit")).thenReturn(testpricePerUnit);
        when(resultSet.getInt("hotel_id")).thenReturn(testhotel_id);
        when(resultSet.getString("description")).thenReturn(testdescription);
        when(resultSet.getInt("discount")).thenReturn(testdiscount);
        tourofferDAO.buildTour(resultSet);
    }

    @Test
    public void getToursByPagePositiveResult() {
        List<TourOffer> listOfTours = new ArrayList<TourOffer>();
        listOfTours.add(expectedTour);
        when(jdbcTemplate.query(anyString(), any(Object[].class), any(RowMapper.class))).thenReturn(listOfTours);
        assertEquals(1, tourofferDAO.getToursByPage(0, 1).size());
    }
}
