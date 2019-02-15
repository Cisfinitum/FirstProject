package com.epam.repository;

import com.epam.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ReservationDAO {
    private JdbcTemplate jdbcTemplate;
    private RowMapper reservationMapper = (rs, rowNum) -> buildReservation(rs);

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private Reservation buildReservation(ResultSet rs) throws SQLException {
        return Reservation.builder()
                .id(rs.getInt("id"))
                .client_id(rs.getInt("client_id"))
                .tourOffer_id(rs.getInt("tourOffer_id"))
                .numberOfPeople(rs.getInt("numberOfPeople"))
                .status(rs.getString("status"))
                .discount_id(rs.getInt("discount_id"))
                .totalPrice(rs.getInt("totalPrice"))
                .build();
    }

    public void addReservation(Reservation reservation) {
        String sql = "INSERT INTO reservation (client_id, tourOffer_id, " +
                "numberOfPeople, status, discount_id, totalPrice) VALUES (?,?,?,?,?,?)";
        jdbcTemplate.update(sql, reservation.getClient_id(), reservation.getTourOffer_id(), reservation.getNumberOfPeople(),
                reservation.getStatus(), reservation.getDiscount_id(), reservation.getTotalPrice());
    }

    public Reservation getReservationById(Integer id) {
        String sql = "SELECT * FROM reservation WHERE id = ?";
        return jdbcTemplate.queryForObject(sql,
                new Object[]{id}, (rs, rowNum) -> buildReservation(rs));
    }

    public List listReservations() {
        String sql = "SELECT * from reservation";
        return jdbcTemplate.query(sql, reservationMapper);
    }

    public void removeReservation(Integer id) {
        String sql = "DELETE FROM reservation WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public void updateReservation(Integer id, Integer Client_id, Integer TourOffer_id, Integer NumberOfPeople, String Status, Integer Discount_id, Integer TotalPrice) {
        String sql = "UPDATE reservatiom SET Client_id = ?, " +
                "TourOffer_id = ? ," +
                "NumberOfPeople = ?," +
                "Status = ?," +
                "Discount_id = ?," +
                "TourOffer_id = ?," +
                "TotalPrice = ? " +
                "WHERE id = ?";
        jdbcTemplate.update(sql, Client_id, TourOffer_id, NumberOfPeople, Status, Discount_id, TotalPrice, id);
    }
}
