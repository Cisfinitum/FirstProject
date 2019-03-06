package com.epam.service;


import com.epam.exception.InvalidDataBaseAffectedException;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


public class PersonDetailsServiceImplTest {


    @Mock
    private BCryptPasswordEncoder testPasswordEncoder;
    @Mock
    private PersonService personService;
    @Mock
    private Person person;

    public static final int EXPECTED_AFFECT = 1;
    public static final int UNEXPECTED_AFFECT = 10;
    public static final int ZERO_AFFECT = 0;
    private Set<GrantedAuthority> roles;
    private PersonDetailsServiceImpl personDetailsServiceImpl;
    private String testEmail = "user";
    private String testPassword = "123";
    private BCryptPasswordEncoder passwordEncoder;
    private String encodedPassword = "encoded";
    private String nonExistEmail = "nonExistingEmail";
    private String testPhoneNumber = "8999999999";
    private String testFirstName = "Example";
    private String testLastName = "Example";
    private Person testPerson;
    private Integer testId;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        personDetailsServiceImpl = new PersonDetailsServiceImpl(personService, testPasswordEncoder);
        roles = new HashSet<>();
        passwordEncoder = new BCryptPasswordEncoder();
        testId = 1;
        testEmail = "user";
        testPassword = "123";
        nonExistEmail = "nonExistingEmail";
        testPerson = new Person(testId, testEmail, encodedPassword, PersonRoleEnum.valueOf("USER"), testPhoneNumber, testFirstName, testLastName);
    }

    @Test
    public void loadUserPositiveResult() {

        when(personService.getPerson(testEmail)).thenReturn(new Person(1, testEmail, testPassword, PersonRoleEnum.ADMIN, testPhoneNumber, testFirstName, testLastName));
        when(person.getRole()).thenReturn(PersonRoleEnum.ADMIN);
        roles.add(new SimpleGrantedAuthority(person.getRole().getEnumRole()));
        UserDetails expectedPerson = new User(testEmail, testPassword, roles);
        UserDetails actualPerson = personDetailsServiceImpl.loadUserByUsername(testEmail);
        assertEquals(expectedPerson.getUsername(), actualPerson.getUsername());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserThrowsException() {
        when(personService.getPerson(nonExistEmail)).thenThrow(UsernameNotFoundException.class);
        when(person.getRole()).thenReturn(PersonRoleEnum.ADMIN);
        roles.add(new SimpleGrantedAuthority(person.getRole().getEnumRole()));
        personDetailsServiceImpl.loadUserByUsername(nonExistEmail);

    }
}