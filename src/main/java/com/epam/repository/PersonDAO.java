package com.epam.repository;

import com.epam.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


import java.sql.ResultSet;
import java.util.List;

@Repository
public class PersonDAO {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getPersons(){
        return jdbcTemplate.query("SELECT * FROM person",
                (ResultSet rs, int rowNum) -> Person.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .build());
    }
}