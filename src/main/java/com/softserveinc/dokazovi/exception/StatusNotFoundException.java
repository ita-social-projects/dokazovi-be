package com.softserveinc.dokazovi.exception;

public class StatusNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Status not found";

    public StatusNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public StatusNotFoundException(String message) {
        super(message);
    }
}
