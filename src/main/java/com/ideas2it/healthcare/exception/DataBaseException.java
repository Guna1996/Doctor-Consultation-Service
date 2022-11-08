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
     * Constructs a {@code SQLException} object with a given
     * {@code reason}. The  {@code SQLState}  is initialized to
     * {@code null} and the vendor code is initialized to 0.
     * <p>
     * The {@code cause} is not initialized, and may subsequently be
     * initialized by a call to the
     * {@link Throwable#initCause(Throwable)} method.
     *
     * @param message a description of the exception
     */
    public DataBaseException(String message) {
        super(message);
    }
}
