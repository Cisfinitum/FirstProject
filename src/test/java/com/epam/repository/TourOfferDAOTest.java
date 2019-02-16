package com.epam.repository;

import com.epam.model.TourOffer;
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
    private String testtourType = "Активный";
    private Date teststartDate = Date.valueOf("20.02.19");
    private Date testendDate = Date.valueOf("26.02.19");
    private Integer testpricePerUnit = 1500;
    private Integer testhotel_id = 1;
    private String testdescription = "Лучший курорт";
    private Integer testdiscount_id = 1;

    @Mock
    private ResultSet resultSet;
    @Mock
    private TourOffer expectedTour;

    @InjectMocks
    private TourOfferDAO tourofferDAO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void buildTourPositiveCheck() throws SQLException {
        int testId = 1;
        when(resultSet.getInt("id")).thenReturn(testId);
        when(resultSet.getString("tourType")).thenReturn(testtourType);
        when(resultSet.getDate("startDate")).thenReturn(teststartDate);
        when(resultSet.getDate("endDate")).thenReturn(testendDate);
        when(resultSet.getInt("pricePerUnit")).thenReturn(testpricePerUnit);
        when(resultSet.getInt("hotel_id")).thenReturn(testhotel_id);
        when(resultSet.getString("description")).thenReturn(testdescription);
        when(resultSet.getInt("discount_id")).thenReturn(testdiscount_id);
        when(expectedTour.getTourType()).thenReturn(testtourType);
        TourOffer actualTourOffer = tourofferDAO.buildTour(resultSet);
        assertEquals(expectedTour.getTourType(), actualTourOffer.getTourType());
    }

    @Test(expected = SQLException.class)
    public void buildTourThrowsException() throws SQLException {
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
