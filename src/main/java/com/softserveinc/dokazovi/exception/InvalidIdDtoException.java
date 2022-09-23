package com.softserveinc.dokazovi.exception;

public class InvalidIdDtoException extends DtoException {

    public InvalidIdDtoException(Object o) {
        super(o + " has invalid id");
    }
}
