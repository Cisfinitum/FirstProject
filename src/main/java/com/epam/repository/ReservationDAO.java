package com.epam.repository;

import com.epam.model.Reservation;
import com.epam.model.ReservationStatusEnum;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@PropertySource("classpath:columns.properties")
@Repository
public class ReservationDAO {
    private final JdbcTemplate jdbcTemplate;
    @Value("${reservation.id}")
    private String id;
    @Value("${reservation.clientId}")
    private String clientId;
    @Value("${reservation.tourOfferId}")
    private String tourOfferId;
    @Value("${reservation.numberOfPeople}")
    private String numberOfPeople;
    @Value("${reservation.status}")
    private String status;
    @Value("${reservation.discountId}")
    private String discountId;
    @Value("${reservation.totalPrice}")
    private String totalPrice;

    private String tableName = "reservation";


    private RowMapper reservationMapper = (rs, rowNum) -> buildReservation(rs);

    @Autowired
    public ReservationDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @SneakyThrows
    Reservation buildReservation(ResultSet rs) {
        return Reservation.builder()
                .id(rs.getInt("id"))
                .clientId(rs.getInt("client_id"))
                .tourOfferId(rs.getInt("tourOffer_id"))
                .numberOfPeople(rs.getInt("numberOfPeople"))
                .status(ReservationStatusEnum.valueOf(rs.getString("status")))
                .discountId(rs.getInt("discount_id"))
                .totalPrice(rs.getInt("totalPrice"))
                .build();
    }

    public int addReservation(Reservation reservation) {
        String sql = "INSERT INTO " + tableName + clientId + tourOfferId + numberOfPeople + status + discountId + totalPrice + ")VALUES( ?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, reservation.getClientId(), reservation.getTourOfferId(), reservation.getNumberOfPeople(),
                reservation.getStatus(), reservation.getDiscountId(), reservation.getTotalPrice());
    }

    public Reservation getReservationById(Integer id) {
        String sql = "SELECT * FROM" + tableName + " WHERE " + id + " = ?";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{id}, (rs, rowNum) -> buildReservation(rs));
    }

    public int getTourOfferById(Integer TourOfferId) {
        String sql = "SELECT tourOffer_id FROM" + tableName + " WHERE " + tourOfferId + " = ?";
        List<Object> tourOffers = jdbcTemplate.query(sql, (rs, rowNub) ->
                buildReservation(rs));
        if (tourOffers.size() != 0) return 1;
        return 0;
    }

    public List<Reservation> listReservations() {
        String sql = "SELECT * from " + tableName;
        return jdbcTemplate.query(sql, reservationMapper);
    }

    public int removeReservation(Integer id) {
        String sql = "DELETE FROM " + tableName + " WHERE" + id + "= ?";
        return jdbcTemplate.update(sql, id);
    }

    public int updateReservation(Reservation reservation) {
        String sql = "UPDATE" +
                tableName + " SET" + clientId + " = ?, " +
                tourOfferId + "= ? ," +
                numberOfPeople + "= ?," +
                status + " = ?," +
                discountId + "= ?," +
                tourOfferId + " = ?," +
                totalPrice + " = ? " +
                "WHERE" + id + "= ?";
        return jdbcTemplate.update(sql, reservation.getClientId(),
                reservation.getTourOfferId(),
                reservation.getNumberOfPeople(),
                reservation.getStatus().getEnumStatus(),
                reservation.getDiscountId(),
                reservation.getTotalPrice(),
                reservation.getId());
    }
}
