/**
 * <p>
 * This is the base package for all the Exception classes.
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.exception;

import com.ideas2it.healthcare.common.ErrorConstants;
import com.ideas2it.healthcare.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
     * @param exception {@link MethodArgumentNotValidException} is caught exception
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, ?>> handleInvalidArgument(
            MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return userResponse.responseEntity(exception.getMessage(), null, HttpStatus.BAD_REQUEST);
    }

    /**
     * <p>
     * This method is used to handle exception occurred while performing
     * CRUD operation in database
     * </p>
     *
     * @param exception {@link NotAccessibleException} is caught exception
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(NotAccessibleException.class)
    public ResponseEntity<Map<String, ?>> handleBusinessException(NotAccessibleException exception) {
        return userResponse.responseEntity(exception.getMessage(), null, HttpStatus.NOT_FOUND);
    }

    /**
     * <p>
     * This method is used to handle the sql exception occurred
     * while running the CRUD operation in database
     * </p>
     *
     * @param exception {@link DataBaseException} is caught exception
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
     * @param exception {@link Exception} is caught exception
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
    public ResponseEntity<Map<String, ?>> handleDataIntegrityException(DataIntegrityViolationException exception) {
        return userResponse
                .responseEntity(exception.getMessage(), null, HttpStatus.CONFLICT);
    }

    /**
     * <p>
     * This method is used to handle the errors occurring when
     * the details of the database access API in use, such as JDBC
     * </p>
     *
     * @param exception {@link DataAccessException} is caught exception
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, ?>> handleDataAccessException(DataAccessException exception) {
        return userResponse
                .responseEntity(exception.getMessage(), null, HttpStatus.SERVICE_UNAVAILABLE);
    }

    /**
     * <p>
     * This method is used to handle the invalid input of the user
     * during the http request
     * </p>
     *
     * @param exception {@link HttpMessageNotReadableException} is caught exception
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, ?>> handleInvalidInputException(HttpMessageNotReadableException exception) {
        ServletUriComponentsBuilder request = ServletUriComponentsBuilder.fromCurrentRequestUri();
        return userResponse
                .responseEntity(ErrorConstants.INVALID_INPUT, null, HttpStatus.BAD_REQUEST);
    }
}
