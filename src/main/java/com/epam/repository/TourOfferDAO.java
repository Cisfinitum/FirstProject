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
    @Value("${tourOffer.id}")
    private String id;
    @Value("${tourOffer.tourType}")
    private String tourType;
    @Value("${tourOffer.startDate}")
    private String startDate;
    @Value("${tourOffer.endDate}")
    private String endDate;
    @Value("${tourOffer.pricePerUnit}")
    private String pricePerUnit;
    @Value("${tourOffer.hotelId}")
    private String hotelId;
    @Value("${tourOffer.description}")
    private String description;
    @Value("${tourOffer.discount}")
    private String discount;

    @Autowired
    public TourOfferDAO(JdbcTemplate JdbcTemplate) {
        this.JdbcTemplate = JdbcTemplate;
    }

    public List<TourOffer> getTours() {
        return JdbcTemplate.query("SELECT * FROM " + tableName + " ORDER BY " + id, (rs, rowNum) -> buildTour(rs));
    }

    public int deleteTour(Integer tourId) {
        return JdbcTemplate.update("DELETE FROM " + tableName + " WHERE " + id + " = ?", tourId);
    public List<TourOffer> getToursByPage(Integer from, Integer offset){
        String sql = "SELECT * from " +tableName+ " LIMIT ? , ?";
        Object[] parameters = new Object[] { from-1, offset };
        return JdbcTemplate.query(sql, parameters, (rs, rowNum) -> buildTour(rs));
    }

    public int deleteTour(Integer tourId){
         return JdbcTemplate.update("DELETE FROM "+tableName+" WHERE id = ?", tourId);
    }

    public int addTour(TourOffer touroffer) {
        return JdbcTemplate.update("INSERT INTO " + tableName + "(" + tourType + " , " + startDate + ", " + endDate + ", " + pricePerUnit + ", " + hotelId + ", " +
                        description + ", " + discount + ") " + "VALUES (?, ?, ?, ?, ?, ?, ?)",
                touroffer.getTourType(), touroffer.getStartDate(), touroffer.getEndDate(), touroffer.getPricePerUnit(), touroffer.getHotelId(), touroffer.getDescription(), touroffer.getDiscount());
    }

    public int updateTour(TourOffer touroffer) {
        return JdbcTemplate.update("UPDATE " + tableName + " SET " +
                        tourType + " = ?, " + startDate + " = ?, " + endDate + " = ?, " + pricePerUnit + " = ?, " + hotelId + " = ?, " + description + " = ?, " + discount + " = ? " +
                        "WHERE " + id + " = ?", touroffer.getTourType(), touroffer.getStartDate(), touroffer.getEndDate(), touroffer.getPricePerUnit(),
                touroffer.getHotelId(), touroffer.getDescription(), touroffer.getDiscount(), touroffer.getId());
    }

    public List<TourOffer> searchTours(List<Integer> listOfHotelsId, LocalDate startDate, LocalDate endDate) {
        String requestSQL = "SELECT * FROM " + tableName + " WHERE start_date ";
        if (startDate != null)
            requestSQL = requestSQL.concat("= '" + startDate + "'");
        else
            requestSQL = requestSQL.concat("IS NOT NULL ");
        requestSQL = requestSQL.concat(" AND end_Date ");
        if (endDate != null)
            requestSQL = requestSQL.concat("='" + endDate + "'");
        else
            requestSQL = requestSQL.concat("IS NOT NULL ");
        requestSQL = requestSQL.concat(" AND " + hotelId + "    ");
        if (listOfHotelsId.size() != 0) {
            requestSQL = requestSQL.concat("IN (");
            for (Integer hotel_id : listOfHotelsId) {
                requestSQL = requestSQL.concat(hotel_id.toString());
                if (listOfHotelsId.get(listOfHotelsId.size() - 1) != hotel_id)
                    requestSQL = requestSQL.concat(",");
            }
            requestSQL = requestSQL.concat(")");
        } else
            requestSQL = requestSQL.concat("IS NOT NULL");
        requestSQL = requestSQL + " ORDER BY " + id;
        return JdbcTemplate.query(requestSQL, (rs, rowNum) -> buildTour(rs));
    }

    public TourOffer getTourById(Integer tourId) {
        Object[] parameters = new Object[]{tourId};
        return JdbcTemplate.queryForObject("SELECT * FROM " + tableName + " WHERE " + id + " = ?", parameters, (rs, rowNum) -> buildTour(rs));
    }

    public int getAmountOfTours() {
        String sql = "SELECT COUNT(*) FROM " +tableName;
        return JdbcTemplate.queryForObject(
                sql, Integer.class);
    }

    @SneakyThrows(SQLException.class)
    TourOffer buildTour(ResultSet rs) {
        return TourOffer.builder()
                .id(rs.getInt(id))
                .tourType(rs.getString(tourType))
                .startDate(rs.getDate(startDate).toLocalDate())
                .endDate(rs.getDate(endDate).toLocalDate())
                .pricePerUnit(rs.getInt(pricePerUnit))
                .hotelId(rs.getInt(hotelId))
                .description(rs.getString(description))
                .discount(rs.getInt(discount))
                .build();
    }

    public int checkIfHotelsIsUsed(Integer id){
        String sql = "SELECT * FROM " + tableName + " WHERE " + hotelId + " = " + id;
        List<TourOffer> tourOffers = JdbcTemplate.query(sql, (rs, rowNum) -> buildTour(rs));
        if (tourOffers.size() != 0) return 1;
        return 0;
    }
}
