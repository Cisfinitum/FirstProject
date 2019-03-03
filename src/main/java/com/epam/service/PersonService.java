package com.epam.service;

import com.epam.model.Person;
import com.epam.model.Reservation;
import com.epam.repository.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonService {

    private final PersonDAO personDAO;

    @Autowired
    public PersonService(PersonDAO personDAO) {
        this.personDAO = personDAO;
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

    public int addPerson(Person person) {
        if (person == null) throw new IllegalArgumentException("Person must be not null");
        return personDAO.addPerson(person);
    }

    public int updatePassword(String email, String password) {
        if (email == null || password == null) {
            throw new IllegalArgumentException("Email and password must be not null");
        }
        return personDAO.updatePassword(email, password);
    }

    public int addToBlackList(Integer id) {
        if (id == null) throw new IllegalArgumentException("Id must be not null");
        return personDAO.addToBlackList(id);
    }

    public int removeFromBlackList(Integer id) {
        if (id == null) throw new IllegalArgumentException("Id must be not null");
        return personDAO.removeFromBlackList(id);
    }
    public int updatePasswordById(Integer id, String password) {
        if (id == null || password == null) {
            throw new IllegalArgumentException("Id and password must be not null");
        }
        return personDAO.updatePasswordById(id, password);
    }

    public int giveAdminRights(Integer id) {
        if (id == null) throw new IllegalArgumentException("Id must be not null");
        return personDAO.giveAdminRights(id);
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

    public int updatePhoneNumberById(Integer id, String phoneNumber) {
        if (id == null || phoneNumber == null) {
            throw new IllegalArgumentException("Id and phone number must be not null");
        }
        return personDAO.updatePhoneNumberById(id, phoneNumber);
    }

    public int updateFirstNameById(Integer id, String firstName) {
        if (id == null || firstName == null) {
            throw new IllegalArgumentException("Id and first name must be not null");
        }
        return personDAO.updateFirstNameById(id, firstName);
    }

    public int updateLastNameById(Integer id, String lastName) {
        if (id == null || lastName == null) {
            throw new IllegalArgumentException("Id and last name must be not null");
        }
        return personDAO.updateLastNameById(id, lastName);
    }

    public int updateEmailById(Integer id, String email) {
        if (id == null || email == null) {
            throw new IllegalArgumentException("Id and email must be not null");
        }
        return personDAO.updateEmailById(id, email);
    }
}
