package com.epam.repository;

import com.epam.model.Person;
import com.epam.model.PersonRoleEnum;
import com.epam.repository.interfaces.SimplePersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PersonDAO {
    @Value("${clients.id}")
    private String id;
    @Value("${clients.email}")
    private String email;
    @Value(("${clients.role}"))
    private String role;
public class PersonDAO implements SimplePersonDAO {

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

    public int addToBlackList(Integer id) {
        if (id < 1) return -1;
        String sql = "UPDATE person SET role = 'BLOCKED' WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public int removeFromBlackList(Integer id) {
        if (id < 1) return -1;
        String sql = "UPDATE person SET role = 'USER' WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public int giveAdminRights(Integer id) {
        if (id < 1) return -1;
        String sql = "UPDATE person SET role = 'ADMIN' WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public int updatePassword(String email, String password) {
        if (email.equals("") || password.equals("")) return -1;
        String sql = "UPDATE person SET password = ? WHERE email = ?";
        return jdbcTemplate.update(sql, password, email);
    }

    public List<Person> listOfUsers(Integer page, Integer numOfRows) {
        page = (page - 1) * numOfRows + 1;
        String sql = "SELECT * FROM person WHERE role != 'ADMIN' ORDER BY id LIMIT " + (page - 1) + "," + numOfRows;
        return jdbcTemplate.query(sql, (rs, rowNum) -> buildPerson(rs));
    }

    public int amountOfUsers() {
        String sql = "SELECT COUNT(*) FROM person WHERE role != 'ADMIN'";
        return jdbcTemplate.queryForObject(sql, new Object[]{}, Integer.class);
    }
}