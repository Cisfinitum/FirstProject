package com.epam.repository.interfaces;

import com.epam.model.Person;

import java.util.List;

/**
 * The base interface for database manipulations with person entity
 *
 * @author Lev Kisilev
 */
public interface SimplePersonDAO {
    /**
     * Return the list of all people from the database
     *
     * @return list of people
     */
    List<Person> getPersons();

    /**
     * Get person's email and tell if there is already a person with such email in the database
     *
     * @param email person email
     * @return true if a person with specified email exist
     * false if such person does not exist
     */
    boolean doesEmailExist(String email);

    /**
     * Get the instance of person and add it in the database
     *
     * @param person the instance of a person
     * @return 1 if the person was added successfully
     * -1 if such person exist already
     */
    int addPerson(Person person);

    /**
     * Get person's email and change person'a role to BLOCKED
     *
     * @param id person's email
     * @return 1 if person's role was changed successfully
     * -1 if the passed email was an empty string
     */
    int addToBlackList(Integer id);

    /**
     * Get person's email and change his role to USER
     *
     * @param id person's email
     * @return 1 if person's role was changed successfully
     * -1 if the passed email was an empty string
     */
    int removeFromBlackList(Integer id);

    /**
     * Get person's email and change his role to ADMIN
     *
     * @param id person's email
     * @return 1 if person's role was changed successfully
     * -1 if the passed email was an empty string
     */
    int giveAdminRights(Integer id);

    /**
     * Get person's email and password
     * Update the value of password in the database
     *
     * @param email    person's email
     * @param password person's password
     * @return 1 if person's password was changed successfully
     * -1 if email or password is an empty string
     */
    int updatePassword(String email, String password);
}
