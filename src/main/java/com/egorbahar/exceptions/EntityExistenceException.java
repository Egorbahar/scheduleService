package com.egorbahar.exceptions;

public class EntityExistenceException extends RuntimeException {

    public EntityExistenceException(String message) {
        super(message);
    }
}