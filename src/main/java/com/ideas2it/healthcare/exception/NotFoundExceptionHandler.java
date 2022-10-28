/**
 * <p>
 * This is the base package for all the Exception classes.
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This is an custom exception handler it acts
 * between dispatcher servlet and controller
 * </p>
 *
 * @author Gunaseelan k
 * @since 2022-10-10
 */@RestControllerAdvice
public class NotFoundExceptionHandler {

    /**
     * <p>
     * This method is used to handle exception occured during validation
     * of user inputs
     * </p>
     *
     * @parm exception is caught exception
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleInvalidArgument(MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return new ResponseEntity<>(errorMap, HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to handle exception occured while performing
     * CRUD operation in database
     * </p>
     *
     * @parm exception is caught exception
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleBusinessException(NotFoundException exception) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("errorMessage", exception.getMessage());
        return new ResponseEntity<>(errorMap, HttpStatus.OK);
    }
}
