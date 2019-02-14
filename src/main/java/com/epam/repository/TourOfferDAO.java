package com.epam.repository;

import com.epam.model.TourOffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TourOfferDAO {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public TourOfferDAO(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TourOffer> getTours(){
        return jdbcTemplate.query("SELECT * FROM tourOffer", (rs, rowNum) -> buildTour(rs));
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
