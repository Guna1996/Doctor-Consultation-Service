/**
 * <p>
 * This is the base package for all the Exception classes.
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.exception;

/**
 * <p>
 * This is an Sql exception handler it acts
 * between dispatcher servlet and controller
 * </p>
 *
 * @author Mohamed Jubair S
 * @since 2022-10-10
 */
public class DataBaseException extends RuntimeException {

    /**
     * <p>
     * This method is used to throw Database error message
     * </p>
     *
     * @param message {@link String} is error message
     */
    public DataBaseException(String message) {
        super(message);
    }
}
