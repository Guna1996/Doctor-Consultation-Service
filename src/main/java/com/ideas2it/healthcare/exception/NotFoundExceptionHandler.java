/**
 * <p>
 * This is the base package for all the Exception classes.
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.exception;

import com.ideas2it.healthcare.response.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
 */
@RestControllerAdvice
public class NotFoundExceptionHandler {

    @Autowired
    CustomResponse customResponse;

    /**
     * <p>
     * This method is used to handle exception occurred during validation
     * of user inputs
     * </p>
     *
     * @param exception {@link HttpStatus} is caught exception
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidArgument(
            MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return customResponse.responseEntity(errorMap, null, HttpStatus.BAD_REQUEST);
    }

    /**
     * <p>
     * This method is used to handle exception occurred while performing
     * CRUD operation in database
     * </p>
     *
     * @param exception {@link HttpStatus} is caught exception
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessException(NotFoundException exception) {
        return customResponse.responseEntity(exception.getMessage(), null, HttpStatus.OK);
    }

    /**
     * <p>
     * This method is used to handle the sql exception occurred
     * while running the CRUD operation in database
     * </p>
     *
     * @param exception {@link HttpStatus} is caught exception
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(SqlException.class)
    public ResponseEntity<Map<String, Object>> handleSqlException(SqlException exception) {
        return customResponse
                .responseEntity(exception.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * <p>
     * This method is used to handle the all the non-customised exception
     * occurred while running the CRUD operation in database
     * </p>
     *
     * @param exception {@link HttpStatus} is caught exception
     * @return {@link ResponseEntity}
     */
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Map<String, Object>> globalException(Exception exception) {
//        return customResponse
//                .responseEntity(exception.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
