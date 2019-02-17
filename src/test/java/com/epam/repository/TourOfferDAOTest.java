package com.epam.repository;

import com.epam.model.TourOffer;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

public class TourOfferDAOTest {
    private String testtourType = "Active";
    private Date teststartDate = Date.valueOf("2018-02-19");
    private Date testendDate = Date.valueOf("2018-02-25");
    private Integer testpricePerUnit = 1500;
    private Integer testhotel_id = 1;
    private String testdescription = "Best tour";
    private Integer testdiscount_id = 1;

    @Mock
    private ResultSet resultSet;
    @Mock
    private TourOffer expectedTour;

    @InjectMocks
    private TourOfferDAO tourofferDAO;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        expectedTour =  new TourOffer(1, "Active", Date.valueOf("2018-02-19"),  Date.valueOf("2018-02-25"),
                1500, 1,"Best tour", 1);
        jdbcTemplate = new JdbcTemplate();
    }

    @Test
    public void buildTourPositiveCheck() throws SQLException {
        int testtourID = 1;
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

//    @Test
//    public void addTour(){
//        tourofferDAO.addTour(expectedTour);
//        List<TourOffer> allTours = tourofferDAO.getTours();
//        assertEquals(allTours.size(),1);
//    }
//
//    @Test
//    public void deleteTour(){
//        tourofferDAO.addTour(expectedTour);
//        List<TourOffer> allTours = tourofferDAO.getTours();
//        assertEquals(allTours.size(),1);
//        tourofferDAO.deleteTour(1);
//        allTours = tourofferDAO.getTours();
//        assertEquals(allTours.size(),0);
//    }
}
