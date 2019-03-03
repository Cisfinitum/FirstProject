package com.epam.repository;

import com.epam.model.Hotel;
import com.epam.repository.interfaces.SimpleHotelDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import lombok.SneakyThrows;

@PropertySource("classpath:columns.properties")
@Repository
public class HotelDAO implements SimpleHotelDAO {
    @Value("${hotel.tableName}")
    private String tableName;
    @Value("${hotel.id}")
    private String id;
    @Value("${hotel.name}")
    private String name;
    @Value("${hotel.city}")
    private String city;
    @Value("${hotel.country}")
    private String country;
    @Value("${hotel.numberOfStars}")
    private String numberOfStars;

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HotelDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Hotel> getHotels() {
        return jdbcTemplate.query("SELECT * FROM " + tableName, (rs, rowNum) -> buildHotel(rs));
    }

    public List<Hotel> getHotelsByCountry(String hotelCountry) {
        Object[] parameters = new Object[]{hotelCountry};
        return jdbcTemplate.query("SELECT * FROM " + tableName + " WHERE " + country + " = ?", parameters, (rs, rowNum) -> buildHotel(rs));
    }

    public Hotel getHotelById(Integer hotelId) {
        Object[] parameters = new Object[]{hotelId};
        return jdbcTemplate.queryForObject("SELECT * FROM " + tableName + " WHERE " + id + " = ?", parameters, (rs, rowNum) -> buildHotel(rs));
    }

    public Integer createHotel(Hotel hotel) {
        String sql = "INSERT INTO " + tableName + " (" + name + ", " + city + ", " + country + ", " + numberOfStars + ") values (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, hotel.getName(), hotel.getCity(), hotel.getCountry(), hotel.getNumberOfStars());
    }

    public Integer updateHotel(Hotel hotel) {
        String sql = "UPDATE " + tableName + " SET " + name + " = ?, " + city + " = ?, " + country + " = ?, " + numberOfStars + " = ? WHERE " + id + " = ?";
        return jdbcTemplate.update(sql, hotel.getName(), hotel.getCity(), hotel.getCountry(), hotel.getNumberOfStars(), hotel.getId());
    }

    public Integer deleteHotel(int hotelId) {
        String sql = "DELETE " + tableName + " WHERE " + id + " = ?";
        return jdbcTemplate.update(sql, hotelId);
    }

    @SneakyThrows(SQLException.class)
    Hotel buildHotel(ResultSet rs) {
        return Hotel.builder()
                .id(rs.getInt(id))
                .name(rs.getString(name))
                .city(rs.getString(city))
                .country(rs.getString(country))
                .numberOfStars(rs.getInt(numberOfStars))
                .build();
    }

}