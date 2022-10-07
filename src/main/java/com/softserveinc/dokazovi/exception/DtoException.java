package com.softserveinc.dokazovi.exception;

public abstract class DtoException extends RuntimeException {

    protected DtoException(String message) {
        super(message);
    }
}
