package com.epam.repository;

import com.epam.model.TourOffer;
import com.epam.repository.interfaces.SimpleTourOfferDAO;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@PropertySource("classpath:columns.properties")
@Repository
public class TourOfferDAO implements SimpleTourOfferDAO {
    private final JdbcTemplate JdbcTemplate;
    @Value("${tourOffer.tableName}")
    private String tableName;
    @Value("${tourOffer.startDate}")
    private String startDateName;
    @Value("${tourOffer.endDate}")
    private String endDateName;
    @Value("${tourOffer.pricePerUnit}")
    private String pricePerUnitName;
    @Value("${tourOffer.hotelId}")
    private String hotelIdName;
    @Value("${tourOffer.description}")
    private String descriptionName;
    @Value("${tourOffer.discountId}")
    private String discountIdName;
    @Value("${tourOffer.id}")
    private String idName;
    @Value("${tourOffer.tourType}")
    private String tourTypeName;

    @Autowired
    public TourOfferDAO(JdbcTemplate JdbcTemplate) {
        this.JdbcTemplate = JdbcTemplate;
    }

    public List<TourOffer> getTours(){
        return JdbcTemplate.query("SELECT * FROM " + tableName, (rs, rowNum) -> buildTour(rs));
    }

    public int deleteTour(Integer tourId){
         return JdbcTemplate.update("DELETE FROM " + tableName + " WHERE " + idName + " = ?", tourId);
    }

    public int addTour(TourOffer touroffer){
        return JdbcTemplate.update("INSERT INTO " + tableName + "(" + tourTypeName + ", " + startDateName +
                        ", " + endDateName + ", " + pricePerUnitName + ", " + hotelIdName + ", " + descriptionName + ", " + discountIdName + ") " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)",touroffer.getTourType(),touroffer.getStartDate(),touroffer.getEndDate(),
                touroffer.getPricePerUnit(),touroffer.getHotelId(),touroffer.getDescription(),touroffer.getDiscountId());
    }

    public int updateTour(TourOffer touroffer){
       return JdbcTemplate.update("UPDATE "+tableName+" SET " + tourTypeName +
               " = ?, " +  startDateName + " = ?, " + endDateName + " = ?, " + pricePerUnitName + " = ?, " + hotelIdName +
                       " = ?, " + descriptionName + " = ?, " + discountIdName + " = ? " +
               "WHERE " + idName + " = ?",touroffer.getTourType(),touroffer.getStartDate(),touroffer.getEndDate(),touroffer.getPricePerUnit(),
               touroffer.getHotelId(),touroffer.getDescription(),touroffer.getDiscountId(),touroffer.getId());
    }

    public List<TourOffer> searchTours(List<Integer> listOfHotelsId, LocalDate startDate, LocalDate endDate, Integer page, Integer total){
        String requestSQL = "SELECT * FROM " + tableName + " WHERE " + startDateName;
        if(startDate != null) {
            requestSQL = requestSQL.concat(" = '" + startDate + "'");
        } else {
            requestSQL = requestSQL.concat(" IS NOT NULL ");
        }
        requestSQL = requestSQL.concat(" AND " + endDateName);
        if(endDate != null) {
            requestSQL = requestSQL.concat(" ='" + endDate + "'");
        } else {
            requestSQL = requestSQL.concat(" IS NOT NULL");
        }
        requestSQL = requestSQL.concat(" AND " + hotelIdName);
        if(listOfHotelsId.size() != 0) {
            requestSQL = requestSQL.concat(" IN (");
            for(Integer hotel_id : listOfHotelsId) {
                requestSQL = requestSQL.concat(hotel_id.toString());
                if(listOfHotelsId.get(listOfHotelsId.size()-1) != hotel_id) {
                    requestSQL = requestSQL.concat(",");
                }
            }
            requestSQL = requestSQL.concat(")");
        } else {
            requestSQL = requestSQL.concat(" IS NOT NULL");
        }
        requestSQL = requestSQL +  " LIMIT " + (page - 1) + "," + total;
        return JdbcTemplate.query(requestSQL, (rs, rowNum) -> buildTour(rs));
    }

    public TourOffer getTourById(Integer tourId){
        Object[] parameters = new Object[] { tourId };
        return JdbcTemplate.queryForObject ("SELECT * FROM "+tableName+" WHERE id = ?", parameters, (rs, rowNum) -> buildTour(rs));
    }

    public int amountOfToursSearched(List<Integer> listOfHotelsId, LocalDate startDate, LocalDate endDate){
        String requestSQL = "SELECT * FROM " + tableName + " WHERE " + startDateName;
        if(startDate != null) {
            requestSQL = requestSQL.concat(" = '" + startDate + "'");
        } else {
            requestSQL = requestSQL.concat(" IS NOT NULL ");
        }
        requestSQL = requestSQL.concat(" AND " + endDateName);
        if(endDate != null) {
            requestSQL = requestSQL.concat(" ='" + endDate + "'");
        } else {
            requestSQL = requestSQL.concat(" IS NOT NULL");
        }
        requestSQL = requestSQL.concat(" AND " + hotelIdName);
        if(listOfHotelsId.size() != 0) {
            requestSQL = requestSQL.concat(" IN (");
            for(Integer hotel_id : listOfHotelsId) {
                requestSQL = requestSQL.concat(hotel_id.toString());
                if(listOfHotelsId.get(listOfHotelsId.size()-1) != hotel_id) {
                    requestSQL = requestSQL.concat(",");
                }
            }
            requestSQL = requestSQL.concat(")");
        } else {
            requestSQL = requestSQL.concat(" IS NOT NULL");
        }
        return JdbcTemplate.queryForObject(requestSQL, new Object[]{}, Integer.class);
    }

    @SneakyThrows(SQLException.class)
    TourOffer buildTour(ResultSet rs){
        return TourOffer.builder()
                .id(rs.getInt(idName))
                .tourType(rs.getString(tourTypeName))
                .startDate(rs.getDate(startDateName).toLocalDate())
                .endDate(rs.getDate(endDateName).toLocalDate())
                .pricePerUnit(rs.getInt(pricePerUnitName))
                .hotelId(rs.getInt(hotelIdName))
                .description(rs.getString(descriptionName))
                .discountId(rs.getInt(discountIdName))
                .build();
    }
}
