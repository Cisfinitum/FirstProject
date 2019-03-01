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
    private String testEmail = "user";
    private String testPassword = "123";
    private String nonExistEmail = "nonExistingEmail";
    private String testPhoneNumber = "8999999999";
    private String testFirstName = "Example";
    private String testLastName = "Example";
    private Person testPerson;
    private Integer testId;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        personDetailsServiceImpl = new PersonDetailsServiceImpl(personService);
        roles = new HashSet<>();
        testId = 1;
        testEmail = "user";
        testPassword = "123";
        nonExistEmail = "nonExistingEmail";
        testPerson = new Person(testId, testEmail, testPassword, PersonRoleEnum.valueOf("USER"), testPhoneNumber, testFirstName, testLastName);
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

    @Test
    public void addPersonPositiveResult() {
        when(personService.addPerson(testPerson)).thenReturn(1);
        assertTrue(personDetailsServiceImpl.addPerson(testPerson));
    }

    @Test(expected = InvalidDataBaseAffectedException.class)
    public void addPersonMoreThanOneRow() {
        when(personService.addPerson(testPerson)).thenReturn(10);
        personDetailsServiceImpl.addPerson(testPerson);
    }

    @Test
    public void addPersonInvalidEmail() {
        when(personService.addPerson(testPerson)).thenReturn(0);
        assertFalse(personDetailsServiceImpl.addPerson(testPerson));
    }

    @Test
    public void addToBlackListPositiveResult() {
        when(personService.addToBlackList(testId)).thenReturn(1);
        assertTrue(personDetailsServiceImpl.addToBlackList(testId));
    }

    @Test(expected = InvalidDataBaseAffectedException.class)
    public void addToBlackListMoreThanOneRow() {
        when(personService.addToBlackList(testId)).thenReturn(10);
        personDetailsServiceImpl.addToBlackList(testId);
    }

    @Test
    public void addToBlackListInvalidEmail() {
        when(personService.addToBlackList(testId)).thenReturn(0);
        assertFalse(personDetailsServiceImpl.addToBlackList(testId));
    }

    @Test
    public void removeFromBlackListPositiveResult() {
        when(personService.removeFromBlackList(testId)).thenReturn(1);
        assertTrue(personDetailsServiceImpl.removeFromBlackList(testId));
    }

    @Test(expected = InvalidDataBaseAffectedException.class)
    public void removeFromBlackListMoreThanOneRow() {
        when(personService.removeFromBlackList(testId)).thenReturn(10);
        personDetailsServiceImpl.removeFromBlackList(testId);
    }

    @Test
    public void removeFromBlackListInvalidEmail() {
        when(personService.removeFromBlackList(testId)).thenReturn(0);
        assertFalse(personDetailsServiceImpl.removeFromBlackList(testId));
    }

    @Test
    public void giveAdminRightsPositiveResult() {
        when(personService.giveAdminRights(testId)).thenReturn(1);
        assertTrue(personDetailsServiceImpl.giveAdminRights(testId));
    }

    @Test(expected = InvalidDataBaseAffectedException.class)
    public void giveAdminRightsMoreThanOneRow() {
        when(personService.giveAdminRights(testId)).thenReturn(10);
        personDetailsServiceImpl.giveAdminRights(testId);
    }

    @Test
    public void giveAdminRightsInvalidEmail() {
        when(personService.giveAdminRights(testId)).thenReturn(0);
        assertFalse(personDetailsServiceImpl.giveAdminRights(testId));
    }

    @Test
    public void updatePasswordPositiveResult() {
        when(personService.updatePassword(testEmail, testPassword)).thenReturn(1);
        assertTrue(personDetailsServiceImpl.updatePassword(testEmail, testPassword));
    }

    @Test(expected = InvalidDataBaseAffectedException.class)
    public void updatePasswordMoreThanOneRow() {
        when(personService.updatePassword(testEmail, testPassword)).thenReturn(10);
        personDetailsServiceImpl.updatePassword(testEmail, testPassword);
    }

    @Test
    public void updatePasswordInvalidEmail() {
        when(personService.updatePassword(testEmail, testPassword)).thenReturn(0);
        assertFalse(personDetailsServiceImpl.updatePassword(testEmail, testPassword));
    }
}