package com.epam.validator;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class ValidatorTest {

    @Test(expected = IllegalArgumentException.class)
    public void checkEmptyThrowException(){
        String emptyString = "";
        Validator.checkEmpty(emptyString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void dateDifferenceThrowException(){
        LocalDate firstDate = LocalDate.of(2020,12,5);
        LocalDate secondDate = LocalDate.of(2018,12,5);
        Validator.dateDifference(firstDate,secondDate);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getIntThrowException(){
        String negativeInt = "-2";
        Integer a = Validator.getInt(negativeInt);
    }

    @Test(expected = NumberFormatException.class)
    public void getIntThrowFormatException(){
        String wrongFormatInt = "sss";
        Integer a = Validator.getInt(wrongFormatInt);
    }

    @Test
    public void getIntPositive(){
        String positiveInt = "1";
        Assert.assertEquals(Validator.getInt(positiveInt),Integer.valueOf(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getDateThrowException(){
        String emptyDate = "";
        LocalDate ld = Validator.getDate(emptyDate,false);
    }

    @Test
    public void getDateNull(){
        String emptyDate = "";
        Assert.assertEquals(Validator.getDate(emptyDate,true),null);
    }

    @Test(expected = NumberFormatException.class)
    public void getDateThrowFormatException(){
        String wrongFormatDate = "ssss ss ss";
        LocalDate ld = Validator.getDate(wrongFormatDate,false);
    }

    @Test
    public void getDatePositive(){
        String anyDate = "2020 12 20";
        LocalDate anyDateSame = LocalDate.of(2020,12,20);
        Assert.assertEquals(Validator.getDate(anyDate,false),anyDateSame);
    }
}
