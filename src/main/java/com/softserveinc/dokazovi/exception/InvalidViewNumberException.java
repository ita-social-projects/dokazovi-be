package com.softserveinc.dokazovi.exception;

public class InvalidViewNumberException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Desired number of views cannot be negative";

    public InvalidViewNumberException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidViewNumberException(String message) {
        super(message);
    }
}