package com.ideas2it.healthcare.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {

        super(message);
    }

    public NotFoundException(String message, Throwable error) {
        super(message, error);
    }
}

