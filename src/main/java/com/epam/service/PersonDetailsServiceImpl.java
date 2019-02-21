package com.epam.service;

import com.epam.exception.InvalidDataBaseAffectedException;
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

    public boolean addPerson(Person person) {
        int result = personService.addPerson(person);
        if (result > 1) throw new InvalidDataBaseAffectedException("Affected more then one row");
        if (result < 1) return false;
        return true;
    }

    public boolean addToBlackList(String email) {
        int result = personService.addToBlackList(email);
        if (result > 1) throw new InvalidDataBaseAffectedException("Affected more then one row");
        if (result < 1) return false;
        return true;
    }

    public boolean removeFromBlackList(String email) {
        int result = personService.removeFromBlackList(email);
        if (result > 1) throw new InvalidDataBaseAffectedException("Affected more then one row");
        if (result < 1) return false;
        return true;
    }

    public boolean giveAdminRights(String email) {
        int result = personService.giveAdminRights(email);
        if (result > 1) throw new InvalidDataBaseAffectedException("Affected more then one row");
        if (result < 1) return false;
        return true;
    }

    public boolean updatePassword(String email, String password) {
        int result = personService.updatePassword(email, password);
        if (result > 1) throw new InvalidDataBaseAffectedException("Affected more then one row");
        if (result < 1) return false;
        return true;
    }
}
