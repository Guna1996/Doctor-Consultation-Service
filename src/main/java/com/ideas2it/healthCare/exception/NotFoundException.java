package com.ideas2it.healthCare.exception;

public class NotFoundException extends Exception{
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException (String message, Throwable error) {
        super(message, error);
    }
}

