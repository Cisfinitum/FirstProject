package com.epam.service;

import com.epam.exception.InvalidDataBaseAffectedException;
import com.epam.model.Person;
import com.epam.model.PersonRoleEnum;
import com.epam.model.Reservation;
import com.epam.repository.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonService {

    private final PersonDAO personDAO;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonDAO personDAO, BCryptPasswordEncoder passwordEncoder) {
        this.personDAO = personDAO;
        this.passwordEncoder = passwordEncoder;
    }

    public Person getPerson(String email) {
        if (email == null) throw new IllegalArgumentException("Email must be not null");
        List<Person> persons = personDAO.getPersons();
        for (Person person : persons) {
            if (person.getEmail().equals(email)) return person;
        }
        return null;
    }

    public Person getPersonById(Integer id) {
        if (id != null) {
            return personDAO.getPersonById(id);
        } else {
            throw new IllegalArgumentException("Id must be specified");
        }
    }

    public boolean addPerson(String email, String password, PersonRoleEnum role, String phoneNumber, String firstName, String lastName) {
        if (email == null) throw new IllegalArgumentException("Email must be not null");
        if (password == null) throw new IllegalArgumentException("Password must be not null");
        if (role == null) throw new IllegalArgumentException("Role must be not null");
        if (phoneNumber == null) throw new IllegalArgumentException("Phone number must be not null");
        if (firstName == null) throw new IllegalArgumentException("First name must be not null");
        if (lastName == null) throw new IllegalArgumentException("Last name must be not null");
        String encodedPassword = passwordEncoder.encode(password);
        Person person = new Person(email, encodedPassword, role, phoneNumber, firstName, lastName);
        int result = personDAO.addPerson(person);
        if (result > 1) throw new InvalidDataBaseAffectedException("Affected more then one row");
        return result == 1;
    }

    public boolean updatePassword(String email, String password) {
        if (email == null || password == null) {
            throw new IllegalArgumentException("Email and password must be not null");
        }
        int result = personDAO.updatePassword(email, password);
        if (result > 1) throw new InvalidDataBaseAffectedException("Affected more then one row");
        if (result < 1) return false;
        return true;
    }

    public boolean addToBlackList(Integer id) {
        if (id == null) throw new IllegalArgumentException("Id must be not null");
        int result = personDAO.addToBlackList(id);
        if (result > 1) throw new InvalidDataBaseAffectedException("Affected more then one row");
        if (result < 1) return false;
        return true;
    }

    public boolean removeFromBlackList(Integer id) {
        if (id == null) throw new IllegalArgumentException("Id must be not null");
        int result = personDAO.removeFromBlackList(id);
        if (result > 1) throw new InvalidDataBaseAffectedException("Affected more then one row");
        if (result < 1) return false;
        return true;
    }

    public boolean updatePasswordById(Integer id, String password) {
        if (id == null || password == null) {
            throw new IllegalArgumentException("Id and password must be not null");
        }
        int result = personDAO.updatePasswordById(id, password);
        if (result > 1) throw new InvalidDataBaseAffectedException("Affected more then one row");
        if (result < 1) return false;
        return true;
    }


    public boolean giveAdminRights(Integer id) {
        if (id == null) throw new IllegalArgumentException("Id must be not null");
        int result = personDAO.giveAdminRights(id);
        if (result > 1) throw new InvalidDataBaseAffectedException("Affected more then one row");
        if (result < 1) return false;
        return true;
    }

    public List<Person> listOfUsers(Integer page, Integer rowNum) {
        if (page == null || !(page > 0) || rowNum == null || !(rowNum > 0)) throw new IllegalArgumentException("Page must be integer and > 0");
        System.out.println(personDAO.listOfUsers(page, rowNum).toString());
        return personDAO.listOfUsers(page, rowNum);
    }

    public int amountOfUsers() { return personDAO.amountOfUsers();}

    public Integer getIdByEmail(String email) {
        if (email.isEmpty()) {
            throw new IllegalArgumentException("Email is an empty string");
        }
        if (email == null) {
            throw new IllegalArgumentException("Email must be not null");
        }
        return personDAO.getIdByEmail(email);
    }

    public Map<Integer,String> mapOfUsersInformation(List<Reservation> reservations) {
        Map<Integer, String> map = new HashMap<>();
        for (Reservation reservation : reservations) {
            Integer clientId = reservation.getClientId();
            Person person = getPersonById(clientId);
            map.put(clientId, person.toString());
        }
        return map;
    }

    public boolean updatePhoneNumberById(Integer id, String phoneNumber) {
        if (id == null || phoneNumber == null) {
            throw new IllegalArgumentException("Id and phone number must be not null");
        }
        int result = personDAO.updatePhoneNumberById(id, phoneNumber);
        if (result > 1) throw new InvalidDataBaseAffectedException("Affected more then one row");
        if (result < 1) return false;
        return true;
    }


    public boolean updateFirstNameById(Integer id, String firstName) {
        if (id == null || firstName == null) {
            throw new IllegalArgumentException("Id and first name must be not null");
        }
        int result = personDAO.updateFirstNameById(id, firstName);
        if (result > 1) throw new InvalidDataBaseAffectedException("Affected more then one row");
        if (result < 1) return false;
        return true;
    }

    public boolean updateLastNameById(Integer id, String lastName) {
        if (id == null || lastName == null) {
            throw new IllegalArgumentException("Id and last name must be not null");
        }
        int result =  personDAO.updateLastNameById(id, lastName);
        if (result > 1) throw new InvalidDataBaseAffectedException("Affected more then one row");
        if (result < 1) return false;
        return true;
    }

    public boolean updateEmailById(Integer id, String email) {
        if (id == null || email == null) {
            throw new IllegalArgumentException("Id and email must be not null");
        }
        int result = personDAO.updateEmailById(id, email);
        if (result > 1) throw new InvalidDataBaseAffectedException("Affected more then one row");
        if (result < 1) return false;
        return true;
    }
}
