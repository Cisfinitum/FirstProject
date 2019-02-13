package com.epam.service;

import com.epam.model.Person;
import com.epam.model.PersonRoleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;


class PersonDetailsServiceImplTest {
    @Mock
    private PersonService personService;
    @Mock
    private Person person;

    private Set<GrantedAuthority> roles;
    private PersonDetailsServiceImpl personDetailsServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        personDetailsServiceImpl = new PersonDetailsServiceImpl(personService);
        roles = new HashSet<>();
    }

    @Test
    void loadUserPositiveResult() {
        String username = "user";
        String password = "123";
        when(personService.getPerson(username)).thenReturn(new Person(1, username, password, PersonRoleEnum.ADMIN));
        when(person.getRole()).thenReturn(PersonRoleEnum.ADMIN);
        roles.add(new SimpleGrantedAuthority(person.getRole().getEnumrole()));
        UserDetails expectedPerson = new User(username, password, roles);
        UserDetails actualPerson = personDetailsServiceImpl.loadUserByUsername(username);
        assertEquals(expectedPerson.getUsername(), actualPerson.getUsername());
    }

    @Test
    void loadUserThrowsException() {
        String nonExistUsername = "1111";
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