package com.epam.service;

import com.epam.model.Person;
import com.epam.model.PersonRoleEnum;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Setter
@Service
public class PersonDetailsServiceImpl implements UserDetailsService{
    private final PersonService personService;

    @Autowired
    public PersonDetailsServiceImpl(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personService.getPerson(username);
        PersonRoleEnum personRoleEnum = person.getRole();
        Set<GrantedAuthority> roles = Stream.of(new SimpleGrantedAuthority
                (Objects.requireNonNullElse(personRoleEnum, PersonRoleEnum.ANONYMOUS).getEnumrole()))
                .collect(Collectors.toCollection(HashSet::new));
        return new org.springframework.security.core.userdetails.User(person.getName(), person.getPassword(), roles);
    }
}
