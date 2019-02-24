package com.epam.validator;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.DateTimeException;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;


@Slf4j
public class Validator {

    @SneakyThrows
    public static LocalDate getDate(String inputDate, Boolean nullResult){
        String nullPointerDate = "Date is empty. User current input Date: ";
        String numberOrDateFormatIssue = "Date or Number format exception. User current input Date: ";
        String[] splitedDate = inputDate.split(" ");
        LocalDate finalDate;
        try {
            if (!inputDate.isEmpty()) {
                finalDate = LocalDate.of(Integer.valueOf(splitedDate[0]), Integer.valueOf(splitedDate[1]),
                        Integer.valueOf(splitedDate[2]));
            } else {
                if(!nullResult) {
                    log.error(nullPointerDate + inputDate);
                    throw new NullPointerException(nullPointerDate + inputDate);
                }
                else
                    finalDate = null;
            }
        } catch (NumberFormatException | DateTimeException e) {
            log.error(numberOrDateFormatIssue + splitedDate[0] + " " +
                    splitedDate[1] + " " + splitedDate[2] + ".");
            throw new Exception(numberOrDateFormatIssue + splitedDate[0] + " " +
                    splitedDate[1] + " " + splitedDate[2] + ".");
        }
        return finalDate;
   }

   @SneakyThrows
    public static Integer getInt(String inputInt){
        String negativeOrZeroIssue = "Integer is negative or 0. User current input Integer: ";
        String numberFormatIssue = "Integer format exception. User current input Integer: ";
        Integer finalInteger;
       try {
           if ((finalInteger=Integer.valueOf(inputInt)) <= 0) {
               log.error(negativeOrZeroIssue+inputInt);
               throw new Exception(negativeOrZeroIssue+inputInt);
           }
       } catch (NumberFormatException e) {
           log.error(numberFormatIssue+inputInt);
           throw new NumberFormatException(numberFormatIssue+inputInt);
       }
       return finalInteger;
   }

   @SneakyThrows
    public static void checkEmpty(String inputString){
       String nullPointerString = "String is empty. User current input String: ";
        if(inputString.isEmpty()){
            log.error(nullPointerString+inputString);
            throw new NullPointerException(nullPointerString+inputString);
        }
   }

   @SneakyThrows
    public static void checkDateDifferent(LocalDate firstDate, LocalDate secondDate){
       String differenceIssue = "Negative or zero difference between dates. User current input Dates difference: ";
       if(!(DAYS.between(firstDate,secondDate)>=1)) {
           log.error(differenceIssue + DAYS.between(firstDate,secondDate));
           throw new Exception(differenceIssue + DAYS.between(firstDate,secondDate));
       }
   }
}
