package com.epam.service;

import com.epam.model.Person;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class PersonDetailsServiceImpl implements UserDetailsService{

    private final PersonService personService;

    @Autowired
    public PersonDetailsServiceImpl(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = personService.getPerson(email);
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(person.getRole().getEnumrole()));
        return new org.springframework.security.core.userdetails.User(person.getEmail(), person.getPassword(), roles);
    }

    public boolean addPersonToDataBase(Person person) {
        return personService.addPersonToDataBase(person);
    }

    public boolean addToBlackList(String email) {
        return personService.addToBlackList(email);
    }

    public boolean removeFromBlackList(String email) {
        return personService.removeFromBlackList(email);
    }

    public boolean giveAdminRights(String email) {
        return personService.giveAdminRights(email);
    }

    public boolean updatePassword(String email, String password) {
        return  personService.updatePassword(email, password);
    }
}
