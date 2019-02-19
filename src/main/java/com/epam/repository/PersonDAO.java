package com.epam.repository;

import com.epam.model.Person;
import com.epam.model.PersonRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getPersons(){
        return jdbcTemplate.query("SELECT * FROM person", (rs, rowNum) -> buildPerson(rs));
    }

    Person buildPerson(ResultSet rs) throws SQLException {
        return Person.builder()
                .id(rs.getInt("id"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .role(PersonRoleEnum.valueOf(rs.getString("role")))
                .build();
    }

    public boolean doesEmailExists(String email) {
        List<String> emailsList = jdbcTemplate.query("SELECT email FROM person", (rs, rowNum) -> getEmail(rs));
        for(String stringEmail:  emailsList){
            System.out.println("Im here");
            if (stringEmail.equals(email)) return true;
        }
        return false;
    }

    String getEmail(ResultSet rs) throws SQLException {
        return rs.getString("email");
    }

    public boolean addPerson(Person person) {
        String email = person.getEmail();
        System.out.println(email);
        if (doesEmailExists(email)) return false;
        String password = person.getPassword();
        System.out.println(password);
        String role = person.getRole().toString();
        System.out.println(role);
        String sql = "INSERT INTO person (email, password, role) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, email, password, role);
        return true;
    }

    public boolean addToBlackList(String email) {
        String sql = "UPDATE person SET role = BLOCKED WHERE email = ?";
        jdbcTemplate.update(sql, email);
        return true;
    }

    public boolean removeFromBlackList(String email) {
        String sql = "UPDATE person SET role = USER WHERE email = ?";
        jdbcTemplate.update(sql, email);
        return true;
    }

    public boolean giveAdminRights(String email) {
        String sql = "UPDATE person SET role = ADMIN WHERE email = ?";
        jdbcTemplate.update(sql, email);
        return true;
    }

    public boolean updatePassword(String email, String password) {
        if (email.equals("") || password.equals("")) return false;
        String sql = "UPDATE person SET password = ? WHERE email = ?";
        jdbcTemplate.update(sql, password, email);
        return true;
    }
}