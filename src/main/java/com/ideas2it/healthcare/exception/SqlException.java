package com.ideas2it.healthcare.exception;

public class SqlException extends RuntimeException {

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
    public SqlException(String message) {
        super(message);
    }
}
