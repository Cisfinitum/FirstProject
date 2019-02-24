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

    public Person getPersonById(Integer personId){
        Object[] parameters = new Object[] { personId };
        return jdbcTemplate.queryForObject("SELECT * FROM person WHERE id = ?  ", parameters, (rs, rowNum) -> buildPerson(rs));
    }

    Person buildPerson(ResultSet rs) throws SQLException {
        return Person.builder()
                .id(rs.getInt("id"))
                .email(rs.getString("email"))
                .password(rs.getString("password"))
                .role(PersonRoleEnum.valueOf(rs.getString("role")))
                .build();
    }

    public boolean doesEmailExist(String email){
        if (email == null) throw new IllegalArgumentException("Illegal email argument");
        if (email.equals("")) return false;
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

    public int addPerson(Person person) {
        String email = person.getEmail();
        if (doesEmailExist(email)) return -1;
        String password = person.getPassword();
        String role = person.getRole().toString();
        String sql = "INSERT INTO person (email, password, role) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, email, password, role);
    }

    public int addToBlackList(String email) {
        if (email.equals("")) return -1;
        String sql = "UPDATE person SET role = BLOCKED WHERE email = ?";
        return jdbcTemplate.update(sql, email);
    }

    public int removeFromBlackList(String email) {
        if (email.equals("")) return -1;
        String sql = "UPDATE person SET role = USER WHERE email = ?";
        return jdbcTemplate.update(sql, email);
    }

    public int giveAdminRights(String email) {
        if (email.equals("")) return -1;
        String sql = "UPDATE person SET role = ADMIN WHERE email = ?";
        return jdbcTemplate.update(sql, email);
    }

    public int updatePassword(String email, String password) {
        if (email.equals("") || password.equals("")) return -1;
        String sql = "UPDATE person SET password = ? WHERE email = ?";
        return jdbcTemplate.update(sql, password, email);
    }

    public int updatePasswordById(Integer id, String password) {
        if (id == null || password.equals("")) return -1;
        String sql = "UPDATE person SET password = ? WHERE id = ?";
        return jdbcTemplate.update(sql, password, id);
    }
}