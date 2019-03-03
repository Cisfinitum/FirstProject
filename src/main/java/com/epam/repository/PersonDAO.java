package com.epam.repository;

import com.epam.model.Person;
import com.epam.model.PersonRoleEnum;
import com.epam.repository.interfaces.SimplePersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@PropertySource("classpath:columns.properties")
@Repository
public class PersonDAO implements SimplePersonDAO {
    @Value("${person.tableName}")
    private String tableName;
    @Value("${person.id}")
    private String id;
    @Value("${person.email}")
    private String email;
    @Value("${person.password}")
    private String password;
    @Value("${person.role}")
    private String role;
    @Value("${person.phoneNumber}")
    private String phoneNumber;
    @Value("${person.firstName}")
    private String firstName;
    @Value("${person.lastName}")
    private String lastName;


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getPersons() {
        return jdbcTemplate.query("SELECT * FROM " + tableName, (rs, rowNum) -> buildPerson(rs));
    }

    public Person getPersonById(Integer personId) {
        Object[] parameters = new Object[]{personId};
        return jdbcTemplate.queryForObject("SELECT * FROM " + tableName + " WHERE " + id + " = ?  ", parameters, (rs, rowNum) -> buildPerson(rs));
    }

    Person buildPerson(ResultSet rs) throws SQLException {
        return Person.builder()
                .id(rs.getInt(id))
                .email(rs.getString(email))
                .password(rs.getString(password))
                .role(PersonRoleEnum.valueOf(rs.getString(role)))
                .phoneNumber(rs.getString(phoneNumber))
                .firstName(rs.getString(firstName))
                .lastName(rs.getString(lastName))
                .build();
    }

    public boolean doesEmailExist(String personEmail) {
        if (personEmail == null) throw new IllegalArgumentException("Illegal email argument");
        if (personEmail.equals("")) return false;
        List<String> emailsList = jdbcTemplate.query("SELECT " + email + " FROM " + tableName, (rs, rowNum) -> getEmail(rs));
        for (String stringEmail : emailsList) {
            if (stringEmail.equals(personEmail)) return true;
        }
        return false;
    }

    String getEmail(ResultSet rs) throws SQLException {
        return rs.getString(email);
    }

    public int addPerson(Person person) {
        String personEmail = person.getEmail();
        if (doesEmailExist(personEmail)) return -1;
        String personPassword = person.getPassword();
        String personRole = person.getRole().toString();
        String personPhoneNumber = person.getPhoneNumber();
        String personFirstName = person.getFirstName();
        String personLastName = person.getLastName();
        String sql = "INSERT INTO " + tableName + " (" + email + ", " + password + ", " + role + ", " + phoneNumber + ", " + firstName + ", " + lastName + ") VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, personEmail, personPassword, personRole, personPhoneNumber, personFirstName, personLastName);
    }

    public int addToBlackList(Integer userId) {
        if (userId < 1) return -1;
        String sql = "UPDATE " + tableName + " SET " + role + " = 'BLOCKED' WHERE " + id + " = ?";
        return jdbcTemplate.update(sql, userId);
    }

    public int removeFromBlackList(Integer userId) {
        if (userId < 1) return -1;
        String sql = "UPDATE " + tableName + " SET " + role + " = 'USER' WHERE " + id + " = ?";
        return jdbcTemplate.update(sql, userId);
    }

    public int giveAdminRights(Integer userId) {
        if (userId < 1) return -1;
        String sql = "UPDATE " + tableName + " SET " + role + " = 'ADMIN' WHERE " + id + " = ?";
        return jdbcTemplate.update(sql, userId);
    }

    public int updatePassword(String userEmail, String userPassword) {
        if (userEmail.equals("") || userPassword.equals("")) return -1;
        String sql = "UPDATE " + tableName + " SET " + password + " = ? WHERE " + email + " = ?";
        return jdbcTemplate.update(sql, userPassword, userEmail);
    }

    public List<Person> listOfUsers(Integer page, Integer numOfRows) {
        page = (page - 1) * numOfRows + 1;
        String sql = "SELECT * FROM " + tableName + " WHERE " + role + " != 'ADMIN' ORDER BY " + id + " LIMIT " + (page - 1) + "," + numOfRows;
        return jdbcTemplate.query(sql, (rs, rowNum) -> buildPerson(rs));
    }

    public int amountOfUsers() {
        String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE " + role + " != 'ADMIN'";
        return jdbcTemplate.queryForObject(sql, new Object[]{}, Integer.class);
    }

    public Integer getIdByEmail(String userEmail) {
        if (!doesEmailExist(userEmail)) {
            return -1;
        }
        String sql = "SELECT " + id + " FROM " + tableName + " WHERE " + email + " = " + "'" + userEmail + "'";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public int updatePasswordById(Integer userId, String userPassword) {
        if (userId < 1 || userPassword.equals("")) return -1;
        String sql = "UPDATE " + tableName + " SET " + password + " = ? WHERE " + id + " = ?";
        return jdbcTemplate.update(sql, userPassword, userId);
    }

    public int updatePhoneNumberById(Integer personId, String personPhoneNumber) {
        if (personId < 1 || personPhoneNumber.equals("")) return -1;
        String sql = "UPDATE " + tableName + " SET " + phoneNumber  + " = ? WHERE "+ id + " = ?";
        return jdbcTemplate.update(sql, personPhoneNumber, personId);
    }

    public int updateFirstNameById(Integer personId, String personFirstName) {
        if (personId < 1 || personFirstName.equals("")) return -1;
        String sql = "UPDATE " + tableName + " SET " + firstName + " = ? WHERE "+ id + " = ?";
        return jdbcTemplate.update(sql, personFirstName, personId);
    }

    public int updateLastNameById(Integer personId, String personLastName) {
        if (personId < 1 || personLastName.equals("")) return -1;
        String sql = "UPDATE " + tableName + " SET " + lastName  + " = ? WHERE "+ id + " = ?";
        return jdbcTemplate.update(sql, personLastName, personId);
    }

    public int updateEmailById(Integer personId, String personEmail) {
        if (personId < 1 || personEmail.equals("") || doesEmailExist(personEmail)) return -1;
        String sql = "UPDATE " + tableName + " SET " + email + " = ? WHERE " + id + " = ?";
        return jdbcTemplate.update(sql, personEmail, personId);
    }

}