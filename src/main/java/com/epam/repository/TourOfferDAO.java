package com.epam.repository;

import com.epam.model.TourOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TourOfferDAO {
    private final JdbcTemplate simpleJdbcTemplate;

    @Autowired
    public TourOfferDAO(JdbcTemplate simpleJdbcTemplate) {
        this.simpleJdbcTemplate = simpleJdbcTemplate;
    }

    public List<TourOffer> getTours(){
        return simpleJdbcTemplate.query("SELECT FROM tourOffer", (rs, rowNum) -> buildTour(rs));
    }

    public int deleteTour(int tourId){
         return simpleJdbcTemplate.update("DELETE FROM tourOffer WHERE id = ?", tourId);
    }

    public int addTour(String tourType, Date startDate, Date endDate, int pricePerUnit, int hotel_id, String description, int discount_id){
        return simpleJdbcTemplate.update("INSERT INTO tourOffer(tourType, startDate, endDate, pricePerUnit, hotel_id, description, discount_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)",tourType,startDate,endDate,pricePerUnit,hotel_id,description,discount_id);
    }

    public int updateTour(String tourType, Date startDate, Date endDate, int pricePerUnit, int hotel_id, String description, int discount_id, int tourId){
       return simpleJdbcTemplate.update("UPDATE tourOffer SET " +
               "tourType = ?, startDate = ?, endDate = ?, pricePerUnit = ?, hotel_id = ?, description = ?, discount_id = ? " +
               "WHERE id = ?",tourType,startDate,endDate,pricePerUnit,hotel_id,description,discount_id,tourId);
    }

    TourOffer buildTour(ResultSet rs) throws SQLException {
        return TourOffer.builder()
                .id(rs.getInt("id"))
                .tourType(rs.getString("tourType"))
                .startDate(rs.getDate("startDate"))
                .endDate(rs.getDate("endDate"))
                .pricePerUnit(rs.getInt("pricePerUnit"))
                .hotel_id(rs.getInt("hotel_id"))
                .description(rs.getString("description"))
                .discount_id(rs.getInt("discount_id"))
                .build();
    }
}
