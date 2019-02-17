package com.epam.repository;

import com.epam.model.TourOffer;
import lombok.SneakyThrows;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

public class TourOfferDAOTest {
    @Mock
    private ResultSet resultSet;
    @Mock
    private TourOffer expectedTour;

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
    }

    @Test
    @SneakyThrows(SQLException.class)
    public void buildTourCheck(){
        Integer testtourID = 1;
        when(resultSet.getInt("id")).thenReturn(testtourID);
        when(resultSet.getString("tourType")).thenReturn(testtourType);
        when(resultSet.getDate("startDate")).thenReturn(teststartDate);
        when(resultSet.getDate("endDate")).thenReturn(testendDate);
        when(resultSet.getInt("pricePerUnit")).thenReturn(testpricePerUnit);
        when(resultSet.getInt("hotel_id")).thenReturn(testhotel_id);
        when(resultSet.getString("description")).thenReturn(testdescription);
        when(resultSet.getInt("discount_id")).thenReturn(testdiscount_id);
        TourOffer actualTourOffer = tourofferDAO.buildTour(resultSet);
        assertEquals(expectedTour, actualTourOffer);
    }

    @Test(expected = SQLException.class)
    @SneakyThrows(SQLException.class)
    public void buildTourThrowsSQLException(){
        when(resultSet.getInt("id")).thenThrow(new SQLException());
        when(resultSet.getString("tourType")).thenReturn(testtourType);
        when(resultSet.getDate("startDate")).thenReturn(teststartDate);
        when(resultSet.getDate("endDate")).thenReturn(testendDate);
        when(resultSet.getInt("pricePerUnit")).thenReturn(testpricePerUnit);
        when(resultSet.getInt("hotel_id")).thenReturn(testhotel_id);
        when(resultSet.getString("description")).thenReturn(testdescription);
        when(resultSet.getInt("discount_id")).thenReturn(testdiscount_id);
        tourofferDAO.buildTour(resultSet);
    }
}
