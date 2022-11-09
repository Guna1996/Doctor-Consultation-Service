/**
 * <p>
 * This is the base package for all the Exception classes.
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.exception;

import com.ideas2it.healthcare.common.Constants;
import com.ideas2it.healthcare.response.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
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
public class ExceptionHandlerAdvice {

    @Autowired
    UserResponse userResponse;

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
    public ResponseEntity<Map<String, ?>> handleInvalidArgument(
            MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return userResponse.responseEntity(errorMap, null, HttpStatus.BAD_REQUEST);
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
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessException(CustomException exception) {
        return customResponse.responseEntity(exception.getMessage(), null, HttpStatus.NOT_FOUND);
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
    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<Map<String, ?>> handleSqlException(DataBaseException exception) {
        return userResponse
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
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, ?>> handleGlobalException(Exception exception) {
        return userResponse
                .responseEntity(exception.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * <p>
     * This method is used to handle the errors occurring when
     * the duplicate details are added into database
     * </p>
     *
     * @param exception {@link HttpStatus} is caught exception
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrityException(DataIntegrityViolationException exception) {
        return customResponse
                .responseEntity(exception.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * <p>
     * This method is used to handle the errors occurring when
     * the details of the database access API in use, such as JDBC
     * </p>
     *
     * @param exception {@link HttpStatus} is caught exception
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, Object>> handleDataAccessException(DataAccessException exception) {
        return customResponse
                .responseEntity(exception.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
