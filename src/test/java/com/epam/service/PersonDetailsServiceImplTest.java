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
    private String testEmail = "user";
    private String testPassword = "123";
    private String nonExistEmail = "nonExistingEmail";
    private Person testPerson;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        personDetailsServiceImpl = new PersonDetailsServiceImpl(personService);
        roles = new HashSet<>();
        testEmail = "user";
        testPassword = "123";
        nonExistEmail = "nonExistingEmail";
        testPerson = new Person(testEmail, testPassword, PersonRoleEnum.valueOf("USER"));
    }

    @Test
    public void loadUserPositiveResult() {

        when(personService.getPerson(testEmail)).thenReturn(new Person(1, testEmail, testPassword, PersonRoleEnum.ADMIN));
        when(person.getRole()).thenReturn(PersonRoleEnum.ADMIN);
        roles.add(new SimpleGrantedAuthority(person.getRole().getEnumrole()));
        UserDetails expectedPerson = new User(testEmail, testPassword, roles);
        UserDetails actualPerson = personDetailsServiceImpl.loadUserByUsername(testEmail);
        assertEquals(expectedPerson.getUsername(), actualPerson.getUsername());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void loadUserThrowsException() {
        when(personService.getPerson(nonExistEmail)).thenThrow(UsernameNotFoundException.class);
        when(person.getRole()).thenReturn(PersonRoleEnum.ADMIN);
        roles.add(new SimpleGrantedAuthority(person.getRole().getEnumrole()));
        personDetailsServiceImpl.loadUserByUsername(nonExistEmail);

    }

    @Test
    public void addPersonPositiveResult() {
        when(personService.addPerson(testPerson)).thenReturn(1);
        assertTrue(personDetailsServiceImpl.addPerson(testPerson));
    }

    @Test(expected = RuntimeException.class)
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
        when(personService.addToBlackList(testEmail)).thenReturn(1);
        assertTrue(personDetailsServiceImpl.addToBlackList(testEmail));
    }

    @Test(expected = RuntimeException.class)
    public void addToBlackListMoreThanOneRow() {
        when(personService.addToBlackList(testEmail)).thenReturn(10);
        personDetailsServiceImpl.addToBlackList(testEmail);
    }

    @Test
    public void addToBlackListInvalidEmail() {
        when(personService.addToBlackList(testEmail)).thenReturn(0);
        assertFalse(personDetailsServiceImpl.addToBlackList(testEmail));
    }

    @Test
    public void removeFromBlackListPositiveResult() {
        when(personService.removeFromBlackList(testEmail)).thenReturn(1);
        assertTrue(personDetailsServiceImpl.removeFromBlackList(testEmail));
    }

    @Test(expected = RuntimeException.class)
    public void removeFromBlackListMoreThanOneRow() {
        when(personService.removeFromBlackList(testEmail)).thenReturn(10);
        personDetailsServiceImpl.removeFromBlackList(testEmail);
    }

    @Test
    public void removeFromBlackListInvalidEmail() {
        when(personService.removeFromBlackList(testEmail)).thenReturn(0);
        assertFalse(personDetailsServiceImpl.removeFromBlackList(testEmail));
    }

    @Test
    public void giveAdminRightsPositiveResult() {
        when(personService.giveAdminRights(testEmail)).thenReturn(1);
        assertTrue(personDetailsServiceImpl.giveAdminRights(testEmail));
    }

    @Test(expected = RuntimeException.class)
    public void giveAdminRightsMoreThanOneRow() {
        when(personService.giveAdminRights(testEmail)).thenReturn(10);
        personDetailsServiceImpl.giveAdminRights(testEmail);
    }

    @Test
    public void giveAdminRightsInvalidEmail() {
        when(personService.giveAdminRights(testEmail)).thenReturn(0);
        assertFalse(personDetailsServiceImpl.giveAdminRights(testEmail));
    }

    @Test
    public void updatePasswordPositiveResult() {
        when(personService.updatePassword(testEmail, testPassword)).thenReturn(1);
        assertTrue(personDetailsServiceImpl.updatePassword(testEmail, testPassword));
    }

    @Test(expected = RuntimeException.class)
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