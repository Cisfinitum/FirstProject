package com.epam.repository;

import com.epam.model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import lombok.SneakyThrows;

@Repository
public class HotelDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HotelDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Hotel> getHotels(){
        return jdbcTemplate.query("SELECT * FROM hotel", (rs, rowNum) -> buildHotel(rs));
    }

    public List<Hotel> getHotelsByCountry (String country) {
        Object[] parameters = new Object[] { country };
        return jdbcTemplate.query ("SELECT * FROM hotel WHERE country = ?", parameters, (rs, rowNum) -> buildHotel(rs));
    }

    public void createHotel (Hotel hotel) {
        String sql = "INSERT INTO hotel (name, city, country, numberOfStars) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql, hotel.getName(), hotel.getCity(), hotel.getCountry(), hotel.getNumberOfStars() );
    }

    public void updateHotel (Hotel hotel) {
        String sql = "UPDATE hotel SET name = ?, city = ?, country = ?, numberOfStars = ? WHERE id = ?";
        jdbcTemplate.update(sql, hotel.getName(), hotel.getCity(), hotel.getCountry(), hotel.getNumberOfStars(), hotel.getId());
    }

    public void deleteHotel (int id) {
        String sql = "DELETE hotel WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @SneakyThrows(SQLException.class)
    Hotel buildHotel(ResultSet rs){
        return Hotel.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("name"))
                .city(rs.getString("city"))
                .country(rs.getString("country"))
                .numberOfStars(rs.getInt("numberOfStars"))
                .build();
    }

}