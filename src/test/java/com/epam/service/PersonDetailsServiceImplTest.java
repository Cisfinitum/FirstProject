package com.epam.service;


import com.epam.model.Person;
import com.epam.model.PersonRoleEnum;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


public class PersonDetailsServiceImplTest {
    @Mock
    private PersonService personService;
    @Mock
    private Person person;

    private Set<GrantedAuthority> roles;
    private PersonDetailsServiceImpl personDetailsServiceImpl;
    String email = "user";
    String password = "123";
    String nonExistEmail = "1111";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        personDetailsServiceImpl = new PersonDetailsServiceImpl(personService);
        roles = new HashSet<>();
    }

    @Test
    public void loadUserPositiveResult() {

        when(personService.getPerson(email)).thenReturn(new Person(1, email, password, PersonRoleEnum.ADMIN));
        when(person.getRole()).thenReturn(PersonRoleEnum.ADMIN);
        roles.add(new SimpleGrantedAuthority(person.getRole().getEnumrole()));
        UserDetails expectedPerson = new User(email, password, roles);
        UserDetails actualPerson = personDetailsServiceImpl.loadUserByUsername(email);
        assertEquals(expectedPerson.getUsername(), actualPerson.getUsername());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserThrowsException() {
        when(personService.getPerson(nonExistEmail)).thenThrow(UsernameNotFoundException.class);
        when(person.getRole()).thenReturn(PersonRoleEnum.ADMIN);
        roles.add(new SimpleGrantedAuthority(person.getRole().getEnumrole()));
        personDetailsServiceImpl.loadUserByUsername(nonExistEmail);

    }
}