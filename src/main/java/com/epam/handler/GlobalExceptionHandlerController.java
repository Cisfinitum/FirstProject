package com.epam.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ControllerAdvice
@EnableWebMvc
public class GlobalExceptionHandlerController {

    private static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultErrorHandler(Exception e){
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
}