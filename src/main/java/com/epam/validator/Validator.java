package com.epam.validator;

import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static LocalDate getDateFromString(String dateString, Boolean getNullIfEmpty){
        try {
            if(!dateString.isEmpty()) {
                 return Date.valueOf(dateString).toLocalDate();
            } else if (getNullIfEmpty) {
                return null;
            } else {
                throw new IllegalArgumentException("Empty Date");
            }
        } catch (NumberFormatException | DateTimeException e) {
                throw new IllegalArgumentException("Wrong Date");
        }
    }

    public static Integer getPriceFromString(String price){
        Pattern pricePattern = Pattern.compile("[0-9]+");
        Matcher priceMatcher = pricePattern.matcher(price);
        if (priceMatcher.matches()) {
            return Integer.valueOf(price);
        } else {
            throw new IllegalArgumentException("Wrong price");
        }
    }

    public static Integer getDiscountFromString(String discount){
        Pattern discountPatten = Pattern.compile("[0-9]{1,3}");
        Matcher discountMatcher = discountPatten.matcher(discount);
        if (discountMatcher.matches() && Integer.valueOf(discount) <= 100) {
            return Integer.valueOf(discount);
        } else {
            throw new IllegalArgumentException("Discount must be from 0 to 100");
        }
    }

    public static String getDescriptionString(String description) {
        if (!description.isEmpty()) {
            return description;
        } else {
            throw new IllegalArgumentException("Empty description");
        }
    }

    public static String getTourTypeString(String tourType) {
        if (!tourType.isEmpty()) {
            return tourType;
        } else {
            throw new IllegalArgumentException("Empty tour type");
        }
    }
}
