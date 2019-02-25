package com.epam.repository;

import com.epam.model.Reservation;
import com.epam.model.ReservationStatusEnum;
import com.epam.repository.interfaces.SimpleReservationDAO;
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
public class ReservationDAO implements SimpleReservationDAO {
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
    @Value("${reservation.tableName}")
    private String tableName;


    private RowMapper reservationMapper = (rs, rowNum) -> buildReservation(rs);

    @Autowired
    public ReservationDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @SneakyThrows
    Reservation buildReservation(ResultSet rs) {
        return Reservation.builder()
                .id(rs.getInt(id))
                .clientId(rs.getInt(clientId))
                .tourOfferId(rs.getInt(tourOfferId))
                .numberOfPeople(rs.getInt(numberOfPeople))
                .status(ReservationStatusEnum.valueOf(rs.getString(status)))
                .discountId(rs.getInt(discountId))
                .totalPrice(rs.getInt(totalPrice))
                .build();
    }

    public int addReservation(Reservation reservation) {
        String sql = "INSERT INTO " + tableName + " (" + clientId + ", " + tourOfferId + ", " + numberOfPeople + ", " + status + ", " + discountId + ", " + totalPrice + ") VALUES ( ?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, reservation.getClientId(), reservation.getTourOfferId(), reservation.getNumberOfPeople(),
                reservation.getStatus().getEnumStatus(), reservation.getDiscountId(), reservation.getTotalPrice());
    }

    public Reservation getReservationById(Integer reservationId) {
        String sql = "SELECT * FROM " + tableName + " WHERE " + id + " = ?";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{reservationId}, (rs, rowNum) -> buildReservation(rs));
    }

    public List<Reservation> getReservationsByPersonId(Integer personId) {
        String sql = "SELECT * FROM " + tableName + " WHERE " + clientId + " = ?";
        Object[] parameters = new Object[] { personId };
        return jdbcTemplate.query(sql, parameters, reservationMapper);
    }

    public int getTourOfferById(Integer offerId) {
        String sql = "SELECT " + tourOfferId + " FROM " + tableName + " WHERE " + tourOfferId + " = ?";
        Object tourOffer = jdbcTemplate.queryForObject(sql, new Object[]{offerId}, (rs, rowNub) ->
                buildReservation(rs));
        if (tourOffer != null) return 1;
        return 0;
    }

    public List<Reservation> listReservations(Integer page, Integer total) {
        String sql = "SELECT * from " + tableName + " LIMIT " + (page - 1) + "," + total;
        return jdbcTemplate.query(sql, reservationMapper);
    }

    public int removeReservation(Integer reservationId) {
        String sql = "DELETE FROM " + tableName + " WHERE " + id + " = ?";
        return jdbcTemplate.update(sql, reservationId);
    }

    public int updateReservation(Reservation reservation) {
        String sql = "UPDATE " +
                tableName + " SET" + clientId + " = ?, " +
                " " + tourOfferId + "= ? ," +
                " " + numberOfPeople + "= ?," +
                " " + status + " = ?," +
                " " + discountId + "= ?," +
                " " + tourOfferId + " = ?," +
                " " + totalPrice + " = ? " +
                "WHERE" + id + "= ?";
        return jdbcTemplate.update(sql, reservation.getClientId(),
                reservation.getTourOfferId(),
                reservation.getNumberOfPeople(),
                reservation.getStatus().getEnumStatus(),
                reservation.getDiscountId(),
                reservation.getTotalPrice(),
                reservation.getId());
    }

    public int amountOfReservations() {
        String sql = "SELECT COUNT(*) FROM " + tableName;
        return jdbcTemplate.queryForObject(
                sql, new Object[]{}, Integer.class);
    }
}
