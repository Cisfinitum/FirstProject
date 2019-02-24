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
    private final JdbcTemplate JdbcTemplate;

    @Autowired
    public TourOfferDAO(JdbcTemplate JdbcTemplate) {
        this.JdbcTemplate = JdbcTemplate;
    }

    public List<TourOffer> getTours(){
        String sql = "SELECT * from tourOffer ";
        return JdbcTemplate.query(sql, (rs, rowNum) -> buildTour(rs));
    }

    public List<TourOffer> getToursByPage(Integer from, Integer offset){
        String sql = "SELECT * from tourOffer LIMIT " + (from - 1) + "," + offset;
        return JdbcTemplate.query(sql, (rs, rowNum) -> buildTour(rs));
    }

    public int deleteTour(Integer tourId){
         return JdbcTemplate.update("DELETE FROM tour_offer WHERE id = ?", tourId);
    }

    public int addTour(TourOffer touroffer){
        return JdbcTemplate.update("INSERT INTO tour_offer(tour_type, start_date, end_date, price_per_unit, hotel_id, description, discount_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)",touroffer.getTourType(),touroffer.getStartDate(),touroffer.getEndDate(),
                touroffer.getPricePerUnit(),touroffer.getHotelId(),touroffer.getDescription(),touroffer.getDiscountId());
    }

    public int updateTour(TourOffer touroffer){
       return JdbcTemplate.update("UPDATE tour_ofer SET " +
               "tour_type = ?, start_date = ?, end_date = ?, price_per_unit = ?, hotel_id = ?, description = ?, discount_id = ? " +
               "WHERE id = ?",touroffer.getTourType(),touroffer.getStartDate(),touroffer.getEndDate(),touroffer.getPricePerUnit(),
               touroffer.getHotelId(),touroffer.getDescription(),touroffer.getDiscountId(),touroffer.getId());
    }

    public List<TourOffer> searchTours(List<Integer> listOfHotelsId, LocalDate startDate, LocalDate endDate){
        String requestSQL = "SELECT * FROM tour_offer WHERE start_date ";
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
        return JdbcTemplate.query(requestSQL, (rs, rowNum) -> buildTour(rs));
    }

    public int getAmountOfTours() {
        String sql = "SELECT COUNT(*) FROM tourOffer";
        return JdbcTemplate.queryForObject(
                sql, Integer.class);
    }

    @SneakyThrows(SQLException.class)
    TourOffer buildTour(ResultSet rs){
        return TourOffer.builder()
                .id(rs.getInt("id"))
                .tourType(rs.getString("tour_type"))
                .startDate(rs.getDate("start_date").toLocalDate())
                .endDate(rs.getDate("end_date").toLocalDate())
                .pricePerUnit(rs.getInt("price_per_unit"))
                .hotelId(rs.getInt("hotel_id"))
                .description(rs.getString("description"))
                .discountId(rs.getInt("discount_id"))
                .build();
    }
}
