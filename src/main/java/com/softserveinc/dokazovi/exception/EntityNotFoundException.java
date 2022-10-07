package com.softserveinc.dokazovi.exception;

public class EntityNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Entity not found";

    public EntityNotFoundException() {
        super(MESSAGE);
    }

    public EntityNotFoundException(String msg) {
        super(msg);
    }
}
