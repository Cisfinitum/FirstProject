package com.epam.repository;

import com.epam.model.Person;
import com.epam.model.PersonRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PersonDAO {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getPersons(){
        return jdbcTemplate.query("SELECT * FROM person", (rs, rowNum) -> buildPerson(rs));
    }

    Person buildPerson(ResultSet rs) throws SQLException {
        return Person.builder()
                .id(rs.getInt("id"))
                .name(rs.getString("email"))
                .password(rs.getString("password"))
                .role(PersonRoleEnum.valueOf(rs.getString("role")))
                .build();
    }

}