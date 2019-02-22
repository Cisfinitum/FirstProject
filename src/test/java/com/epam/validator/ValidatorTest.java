package com.epam.validator;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class ValidatorTest {

    @Test(expected = NullPointerException.class)
    public void checkEmptyThrowException(){
        String emptyString = "";
        Validator.checkEmpty(emptyString);
    }

    @Test(expected = Exception.class)
    public void checkDateDifferentThrowException(){
        LocalDate firstDate = LocalDate.of(2020,12,5);
        LocalDate secondDate = LocalDate.of(2018,12,5);
        Validator.checkDateDifferent(firstDate,secondDate);
    }

    @Test(expected = Exception.class)
    public void getIntThrowException(){
        String anyInt = "-2";
        Integer a = Validator.getInt(anyInt);
    }

    @Test(expected = NumberFormatException.class)
    public void getIntThrowFormatException(){
        String anyInt = "sss";
        Integer a = Validator.getInt(anyInt);
    }

    @Test
    public void getIntPositive(){
        String anyInt = "1";
        Assert.assertEquals(Validator.getInt(anyInt),Integer.valueOf(1));
    }

    @Test(expected = NullPointerException.class)
    public void getDateThrowException(){
        String emptyDate = "";
        LocalDate ld = Validator.getDate(emptyDate,false);
    }

    @Test
    public void getDateNull(){
        String emptyDate = "";
        Assert.assertEquals(Validator.getDate(emptyDate,true),null);
    }

    @Test(expected = Exception.class)
    public void getDateThrowFormatException(){
        String anyDate = "ssss ss ss";
        LocalDate ld = Validator.getDate(anyDate,false);
    }

    @Test
    public void getDatePositive(){
        String anyDate = "2020 12 20";
        LocalDate anyDateSame = LocalDate.of(2020,12,20);
        Assert.assertEquals(Validator.getDate(anyDate,false),anyDateSame);
    }
}
