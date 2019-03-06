package com.epam.validator;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

import static org.mockito.Mockito.when;

public class ValidatorTest {

    @Test(expected = IllegalArgumentException.class)
    @SneakyThrows
    public void getPriceFromStringThrowException() {
        when(Validator.getPriceFromString("test")).thenThrow(IllegalArgumentException.class);
    }

    @Test
    public void getPriceFromString() {
        Assert.assertEquals(Validator.getPriceFromString("10000"),(Integer)10000);
    }

    @Test(expected = IllegalArgumentException.class)
    @SneakyThrows
    public void getDiscountFromStringThrowExceptionByMatcher() {
        when(Validator.getDiscountFromString("test")).thenThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    @SneakyThrows
    public void getDiscountFromStringThrowExceptionByBigValue() {
        when(Validator.getDiscountFromString("150")).thenThrow(IllegalArgumentException.class);
    }

    @Test
    public void getDiscountFromString() {
        Assert.assertEquals(Validator.getDiscountFromString("50"),(Integer)50);
    }

    @Test(expected = IllegalArgumentException.class)
    @SneakyThrows
    public void getTourTypeStringThrowException() {
        when(Validator.getTourTypeString("")).thenThrow(IllegalArgumentException.class);
    }

    @Test
    public void getTourTypeString() {
        Assert.assertEquals(Validator.getTourTypeString("test"),"test");
    }

    @Test(expected = IllegalArgumentException.class)
    @SneakyThrows
    public void getDescriptionStringThrowException() {
        when(Validator.getDescriptionString("")).thenThrow(IllegalArgumentException.class);
    }

    @Test
    public void getDescriptionString() {
        Assert.assertEquals(Validator.getDescriptionString("test"),"test");
    }

    @Test
    public void getDateFromString() {
        Assert.assertEquals(Validator.getDateFromString("2010-10-10",false), LocalDate.of(2010,10,10));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getDateFromStringDateTimeException () {
       Validator.getDateFromString("blablabla",false);
    }

    @Test
    public void getDateFromStringEmptyToNull() {
        Assert.assertNull(Validator.getDateFromString("", true));
    }

    @Test(expected = IllegalArgumentException.class)
    @SneakyThrows
    public void getDateFromStringThrowExceptionIfEmpty() {
        when(Validator.getDateFromString("",false)).thenThrow(IllegalArgumentException.class);
    }
}
