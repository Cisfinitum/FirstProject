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
                .phoneNumber(rs.getString("phoneNumber"))
                .firstName(rs.getString("firstName"))
                .lastName(rs.getString("lastName"))
                .build();
    }

    public boolean doesEmailExist(String email){
        if (email == null) throw new IllegalArgumentException("Illegal email argument");
        if (email.equals("")) return false;
        List<String> emailsList = jdbcTemplate.query("SELECT email FROM person", (rs, rowNum) -> getEmail(rs));
        for(String stringEmail:  emailsList){
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
        String phoneNumber = person.getPhoneNumber();
        String firstName = person.getFirstName();
        String lastName = person.getLastName();
        String sql = "INSERT INTO person (email, password, role, phoneNumber, firstName, lastName) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, email, password, role, phoneNumber, firstName, lastName);
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

    public Integer getIdByEmail(String email) {
        if (!doesEmailExist(email)) {
            return -1;
        }
        String sql = "SELECT id FROM person WHERE email = " + "'"+email+ "'";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public int updatePasswordById(Integer id, String password) {
        if (id < 1 || password.equals("")) return -1;
        String sql = "UPDATE person SET password = ? WHERE id = ?";
        return jdbcTemplate.update(sql, password, id);
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