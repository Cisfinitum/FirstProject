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
    String username = "user";
    String password = "123";
    String nonExistUsername = "1111";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        personDetailsServiceImpl = new PersonDetailsServiceImpl(personService);
        roles = new HashSet<>();
    }

    @Test
    public void loadUserPositiveResult() {

        when(personService.getPerson(username)).thenReturn(new Person(1, username, password, PersonRoleEnum.ADMIN));
        when(person.getRole()).thenReturn(PersonRoleEnum.ADMIN);
        roles.add(new SimpleGrantedAuthority(person.getRole().getEnumrole()));
        UserDetails expectedPerson = new User(username, password, roles);
        UserDetails actualPerson = personDetailsServiceImpl.loadUserByUsername(username);
        assertEquals(expectedPerson.getUsername(), actualPerson.getUsername());
    }

    @Test
    public void loadUserThrowsException() {
        when(personService.getPerson(nonExistUsername)).thenThrow(UsernameNotFoundException.class);
        when(person.getRole()).thenReturn(PersonRoleEnum.ADMIN);
        roles.add(new SimpleGrantedAuthority(person.getRole().getEnumrole()));
        try {
            personDetailsServiceImpl.loadUserByUsername(nonExistUsername);
            fail();
        } catch (UsernameNotFoundException thrown) {
            assertNotNull(thrown);
        }
    }
}