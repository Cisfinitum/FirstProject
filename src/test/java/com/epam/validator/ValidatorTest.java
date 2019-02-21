package com.epam.validator;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class ValidatorTest {

    @Test(expected = NullPointerException.class)
    public void checkEmptyThrowException(){
        Validator.checkEmpty("");
    }

    @Test(expected = Exception.class)
    public void checkDateDifferentThrowException(){
        Validator.checkDateDifferent(LocalDate.of(2020,12,5),LocalDate.of(2018,12,5));
    }

    @Test(expected = Exception.class)
    public void getIntThrowException(){
        Integer a = Validator.getInt("-2");
    }

    @Test(expected = NumberFormatException.class)
    public void getIntThrowFormatException(){
        Integer a = Validator.getInt("sss");
    }

    @Test
    public void getIntPositive(){
        Assert.assertEquals(Validator.getInt("1"),Integer.valueOf(1));
    }

    @Test(expected = NullPointerException.class)
    public void getDateThrowException(){
        LocalDate ld = Validator.getDate("",false);
    }

    @Test
    public void getDateNull(){
        Assert.assertEquals(Validator.getDate("",true),null);
    }

    @Test(expected = Exception.class)
    public void getDateThrowFormatException(){
        LocalDate ld = Validator.getDate("ssss ss ss",false);
    }

    @Test
    public void getDatePositive(){
        Assert.assertEquals(Validator.getDate("2020 12 20",false),LocalDate.of(2020,12,20));
    }
}
