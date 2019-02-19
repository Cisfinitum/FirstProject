package com.epam.repository;

import com.epam.model.TourOffer;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class TourOfferDAO {
    private final JdbcTemplate simpleJdbcTemplate;

    @Autowired
    public TourOfferDAO(JdbcTemplate simpleJdbcTemplate) {
        this.simpleJdbcTemplate = simpleJdbcTemplate;
    }

    public List<TourOffer> getTours(){
        return simpleJdbcTemplate.query("SELECT * FROM tourOffer", (rs, rowNum) -> buildTour(rs));
    }

    public int deleteTour(Integer tourId){
         return simpleJdbcTemplate.update("DELETE FROM tourOffer WHERE id = ?", tourId);
    }

    public int addTour(TourOffer touroffer){
        return simpleJdbcTemplate.update("INSERT INTO tourOffer(tourType, startDate, endDate, pricePerUnit, hotel_id, description, discount_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)",touroffer.getTourType(),touroffer.getStartDate(),touroffer.getEndDate(),
                touroffer.getPricePerUnit(),touroffer.getHotelId(),touroffer.getDescription(),touroffer.getDiscountId());
    }

    public int updateTour(TourOffer touroffer){
       return simpleJdbcTemplate.update("UPDATE tourOffer SET " +
               "tourType = ?, startDate = ?, endDate = ?, pricePerUnit = ?, hotel_id = ?, description = ?, discount_id = ? " +
               "WHERE id = ?",touroffer.getTourType(),touroffer.getStartDate(),touroffer.getEndDate(),touroffer.getPricePerUnit(),
               touroffer.getHotelId(),touroffer.getDescription(),touroffer.getDiscountId(),touroffer.getId());
    }


    public List<TourOffer> searchTours(List<Integer> listOfHotelsId, LocalDate startDate, LocalDate endDate){
        String requestSQL = "SELECT * FROM tourOffer WHERE startDate ";
        if(startDate!=null)
            requestSQL = requestSQL.concat("= '"+startDate+"'");
        else
            requestSQL = requestSQL.concat("IS NOT NULL ");
        requestSQL = requestSQL.concat(" AND endDate ");
        if(endDate!=null)
            requestSQL = requestSQL.concat("='"+endDate+"'");
        else
            requestSQL = requestSQL.concat("IS NOT NULL ");
        requestSQL = requestSQL.concat(" AND hotel_id ");
        if(listOfHotelsId!=null) {
            requestSQL = requestSQL.concat("IN (");
            for(Integer hotel_id: listOfHotelsId){
                requestSQL = requestSQL.concat(hotel_id.toString());
                if(listOfHotelsId.get(listOfHotelsId.size()-1)!=hotel_id)
                    requestSQL = requestSQL.concat(",");
            }
            requestSQL = requestSQL.concat(")");
        }
        else
            requestSQL = requestSQL.concat("IS NOT NULL");
        return simpleJdbcTemplate.query(requestSQL, (rs, rowNum) -> buildTour(rs));
    }


    @SneakyThrows(SQLException.class)
    TourOffer buildTour(ResultSet rs){
        return TourOffer.builder()
                .id(rs.getInt("id"))
                .tourType(rs.getString("tourType"))
                .startDate(rs.getDate("startDate").toLocalDate())
                .endDate(rs.getDate("endDate").toLocalDate())
                .pricePerUnit(rs.getInt("pricePerUnit"))
                .hotelId(rs.getInt("hotel_id"))
                .description(rs.getString("description"))
                .discountId(rs.getInt("discount_id"))
                .build();
    }
}
