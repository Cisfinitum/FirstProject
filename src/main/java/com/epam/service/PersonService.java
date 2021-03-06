package com.epam.service;

import com.epam.model.Person;
import com.epam.repository.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonDAO personDAO;
    @Autowired
    public PersonService(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public Person getPerson(String name){
        List<Person> persons = personDAO.getPersons();
        for (Person person: persons) {
            if (person.getEmail().equals(name)) return person;
        }
        return null;
    }

}
