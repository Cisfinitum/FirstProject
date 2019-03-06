package com.epam.handler;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static org.junit.Assert.*;

public class GlobalExceptionHandlerControllerTest {
    @InjectMocks
    GlobalExceptionHandlerController globalExceptionHandlerController;
    ModelAndView modelAndView;
    private String expectedView;
    private String actualView;
    Exception exception;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        exception = new Exception("test");
    }

    @Test
    public void defaultErrorHandler() {
        expectedView =  "error";
        modelAndView = globalExceptionHandlerController.defaultErrorHandler(exception);
        actualView = modelAndView.getViewName();
        assertEquals(expectedView, actualView);
    }
    @Test
    public void defaultErrorHandlerCheckMessage() {
        String expectedMessage =  "java.lang.Exception: test";
        ModelAndView actual = globalExceptionHandlerController.defaultErrorHandler(exception);
        Map<String, Object> actualModel = actual.getModel();
        assertEquals(expectedMessage, actualModel.get("exception").toString());
    }
}