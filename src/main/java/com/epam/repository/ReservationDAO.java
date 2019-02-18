package com.epam.repository;

import com.epam.model.Reservation;
import com.epam.model.ReservationStatusEnum;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ReservationDAO {

    private final JdbcTemplate jdbcTemplate;

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
        String sql = "INSERT INTO reservation (client_id, tourOffer_id, " +
                "numberOfPeople, status, discount_id, totalPrice) VALUES (?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, reservation.getClientId(), reservation.getTourOfferId(), reservation.getNumberOfPeople(),
                reservation.getStatus(), reservation.getDiscountId(), reservation.getTotalPrice());
    }

    public Reservation getReservationById(Integer id) {
        String sql = "SELECT * FROM reservation WHERE id = ?";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{id}, (rs, rowNum) -> buildReservation(rs));
    }

    public List<Reservation> listReservations() {
        String sql = "SELECT * from reservation";
        return jdbcTemplate.query(sql, reservationMapper);
    }

    public int removeReservation(Integer id) {
        String sql = "DELETE FROM reservation WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public int updateReservation(Reservation reservation) {
        String sql = "UPDATE reservatiom SET client_id = ?, " +
                "TourOffer_id = ? ," +
                "NumberOfPeople = ?," +
                "Status = ?," +
                "Discount_id = ?," +
                "TourOffer_id = ?," +
                "TotalPrice = ? " +
                "WHERE id = ?";
        return jdbcTemplate.update(sql, reservation.getClientId(),
                reservation.getTourOfferId(),
                reservation.getNumberOfPeople(),
                reservation.getStatus().getEnumStatus(),
                reservation.getDiscountId(),
                reservation.getTotalPrice(),
                reservation.getId());
    }
}
