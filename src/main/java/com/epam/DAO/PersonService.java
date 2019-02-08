package com.epam.DAO;

import com.epam.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


import java.sql.ResultSet;
import java.util.List;

@Service
public class PersonService {


    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public PersonService(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getPersons(){
        return jdbcTemplate.query("SELECT * FROM person",
                (ResultSet rs, int rowNum) -> {
                    Person p = new Person();
                    p.setId(rs.getInt("id"));
                    p.setName(rs.getString("name"));

                    return p;
                });
    }
}