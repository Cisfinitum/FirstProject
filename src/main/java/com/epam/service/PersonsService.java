package com.epam.service;


import com.epam.model.Person;
import com.epam.repository.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonsService {

    private final PersonDAO personDAO;
    @Autowired
    public PersonsService(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public void printPersonsNames(){
        List<Person> persons = personDAO.getPersons();
        persons.forEach(person -> System.out.println(person.getName()));
    }
}
