package com.epam.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.mockito.Mockito.when;


public class MainControllerTest {

    @InjectMocks
    MainController controller;
    @Mock
    ModelAndView modelAndView;
    @Mock
    RedirectAttributes redirectAttributes;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void homePage(){
        String actualView = controller.homePage();
        Assert.assertEquals("/homepage",actualView);
    }

    @Test
    public void mainPage(){
        String actualView = controller.mainPage();
        Assert.assertEquals("homepage",actualView);
    }

    @Test
    public void testUser(){
        String actualView = controller.testUser();
        Assert.assertEquals("testuser",actualView);
    }

    @Test
    public void testPage(){
        String actualView = controller.testPage();
        Assert.assertEquals("testpage",actualView);
    }

    @Test
    public void index(){
        String actualView = controller.index();
        Assert.assertEquals("homepage",actualView);
    }

    @Test
    public void contacts(){
        String actualView = controller.contacts();
        Assert.assertEquals("contacts",actualView);
    }

    @Test
    public void badRequest(){
        String actualView = controller.badRequest();
        Assert.assertEquals("403",actualView);
    }

    @Test
    public void accessDeniedPage(){
        String actualView = controller.accessDeniedPage();
        Assert.assertEquals("/access-denied",actualView);
    }

    @Test
    public void notFoundPage(){
        String actualView = controller.notFoundPage();
        Assert.assertEquals("404",actualView);
    }

    @Test
    public void info(){
        String actualView = controller.info();
        Assert.assertEquals("info",actualView);
    }

    @Test
    public void loginPageRedirNull(){
        String actualView = controller.loginPage(modelAndView,null);
        Assert.assertEquals("/login",actualView);
    }

    @Test
    public void loginPageRedirNotNull(){
        String actualView = controller.loginPage(modelAndView,redirectAttributes);
        Assert.assertEquals("/login",actualView);
    }
}
