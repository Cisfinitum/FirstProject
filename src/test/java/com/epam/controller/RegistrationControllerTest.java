package com.epam.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.epam.model.PersonRoleEnum;
import com.epam.service.PersonService;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


public class RegistrationControllerTest {

  @Mock
  RedirectAttributes redirectAttributes;

  @Mock
  private PersonService personService;

  @Spy
  ModelAndView modelAndView;

  @InjectMocks
  RegistrationController registrationController;

  private String email = "email123@gmail.com";
  private String password = "Afynf567";
  private String second_password = "Afynf567";
  private String phoneNumber = "79990346041";
  private String firstName = "A";
  private String lastName = "B";

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void registrationPage() {
    String expectedResult = "/registration";
    String actualResult = registrationController.registrationPage();
    assertEquals(expectedResult, actualResult);
  }

  @Test
  public void deleteReservation() {
    when(personService.addPerson(email,password, PersonRoleEnum.USER,phoneNumber,firstName,lastName)).thenReturn(true);

    ModelAndView actual = registrationController.createUserAccIfPossible(email, password, second_password, phoneNumber,
            firstName, lastName, modelAndView, redirectAttributes);

    assertEquals("redirect:login", actual.getViewName());
  }

  @Test
  public void createUserAccIfPossibleUserExists() {
    when(personService.addPerson(email,password, PersonRoleEnum.USER,phoneNumber,firstName,lastName)).thenReturn(false);

    ModelAndView actual = registrationController.createUserAccIfPossible(email, password, second_password, phoneNumber,
        firstName, lastName, modelAndView, redirectAttributes);

    Map<String, Object> actualModel = actual.getModel();
    assertEquals("Current email already exists", actualModel.get("message"));
  }

  @Test
  public void createUserAccIfPossibleEmailInvalid() {

    ModelAndView actual = registrationController.createUserAccIfPossible("", password, second_password, phoneNumber,
        firstName, lastName, modelAndView, redirectAttributes);

    Map<String, Object> actualModel = actual.getModel();
    assertEquals("Email doesn't match the pattern", actualModel.get("message"));
  }

  @Test
  public void createUserAccIfPossiblePassInvalid() {

    ModelAndView actual = registrationController.createUserAccIfPossible(email, "", "", phoneNumber,
        firstName, lastName, modelAndView, redirectAttributes);

    Map<String, Object> actualModel = actual.getModel();
    assertEquals("Password doesn't match the pattern", actualModel.get("message"));
  }

  @Test
  public void createUserAccIfPossiblePassNotEauals() {

    ModelAndView actual = registrationController.createUserAccIfPossible(email, password, "1234567890",
        phoneNumber, firstName, lastName, modelAndView, redirectAttributes);

    Map<String, Object> actualModel = actual.getModel();
    assertEquals("Passwords are not equal", actualModel.get("message"));
  }

  @Test
  public void createUserAccIfPossibleFisrtName() {

    ModelAndView actual = registrationController.createUserAccIfPossible(email, password, second_password,
        phoneNumber, "", lastName, modelAndView, redirectAttributes);

    Map<String, Object> actualModel = actual.getModel();
    assertEquals("Name must be not empty", actualModel.get("message"));
  }

  @Test
  public void createUserAccIfPossibleFisrtNameNull() {

    ModelAndView actual = registrationController.createUserAccIfPossible(email, password, second_password,
        phoneNumber, null, lastName, modelAndView, redirectAttributes);

    Map<String, Object> actualModel = actual.getModel();
    assertEquals("Name must be not empty", actualModel.get("message"));
  }

  @Test
  public void createUserAccIfPossibleSecondName() {

    ModelAndView actual = registrationController.createUserAccIfPossible(email, password, second_password,
        phoneNumber, firstName, "", modelAndView, redirectAttributes);

    Map<String, Object> actualModel = actual.getModel();
    assertEquals("Name must be not empty", actualModel.get("message"));
  }

  @Test
  public void createUserAccIfPossibleSecondNameNull() {

    ModelAndView actual = registrationController.createUserAccIfPossible(email, password, second_password,
        phoneNumber, firstName, null, modelAndView, redirectAttributes);

    Map<String, Object> actualModel = actual.getModel();
    assertEquals("Name must be not empty", actualModel.get("message"));
  }

  @Test
  public void createUserAccIfPossiblePhneNumber() {

    ModelAndView actual = registrationController.createUserAccIfPossible(email, password, second_password,
        "123", firstName, lastName, modelAndView, redirectAttributes);

    Map<String, Object> actualModel = actual.getModel();
    assertEquals("Phone number is not valid", actualModel.get("message"));
  }

}