package com.epam.exception;

public class InvalidDataBaseAffectedException extends RuntimeException{
    public InvalidDataBaseAffectedException(String msg) {
        super(msg);
    }
}
