/**
 * <p>
 * This is the base package for all the Exception classes.
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * <p>
 * This is an custom exception class and it Extends
 * RuntimeException class
 * </p>
 *
 * @author Gunaseelan k
 * @since 2022-10-10
 */
public class NotFoundException extends RuntimeException {

    /**
     * <p>
     * This method is used to throw customized error message
     * </p>
     *
     * @param message {@link String} is error message
     */
    public NotFoundException(String message) {
        super(message);
    }

    /**
     * <p>
     * This method is used to throw customized error message and caught error
     * </p>
     *
     * @param message {@link String} is error message
     * @param error {@link Throwable} is caught error
     */
    public NotFoundException(String message, Throwable error) {
        super(message, error);
    }
}

