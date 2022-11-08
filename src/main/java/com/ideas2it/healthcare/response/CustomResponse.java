/**
 * <p>
 * This package contains class which is used to send response in a
 * customised format with proper syntax
 * </p>
 * Copyright 2022 - Ideas2it
 */
package com.ideas2it.healthcare.response;

import com.ideas2it.healthcare.common.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * This Custom response class is used to send response in a
 * customised format with proper syntax
 * </p>
 *
 * @author Mohamed Jubair
 * @version 1
 * @since 2022-10-10
 */

@Component
public class CustomResponse {

    /**
     * <p>
     * This method is used send response in proper syntax for all CRUD operations
     * in the web application
     * </p>
     *
     * @param message {@link String} is response message
     * @param entity  {@link Object} is response Dto object
     * @param status  {@link HttpStatus} is http response status
     * @return {@link ResponseEntity}
     */
    public ResponseEntity<Map<String, Object>> responseEntity
    (String message, Object entity, HttpStatus status) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put(Constants.MESSAGE, message);
        response.put(Constants.ENTITY, entity);
        response.put(Constants.ENTITY_LIST, null);
        response.put(Constants.RESPONSE_CODE, status.value());
        return new ResponseEntity<>(response, status);
    }

    /**
     * <p>
     * This method is used send response in proper syntax for all CRUD operations
     * in the web application with pagination
     * </p>
     *
     * @param message    {@link String} is response message
     * @param entityList {@link List<?>} is response Dto List
     * @param status     {@link HttpStatus} is http response status
     * @param totalPages {@link Double} is number of pages required
     * @return {@link ResponseEntity}
     */
    public ResponseEntity<Map<String, Object>> responseEntity
    (String message, List<?> entityList, HttpStatus status, Integer totalPages) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put(Constants.MESSAGE, message);
        response.put(Constants.ENTITY, null);
        response.put(Constants.ENTITY_LIST, entityList);
        response.put(Constants.RESPONSE_CODE, status.value());
        response.put(Constants.TOTAL_PAGES, totalPages);
        return new ResponseEntity<>(response, status);
    }

    /**
     * <p>
     * This method is used send  error response in proper syntax for all CRUD operations
     * in the web application
     * </p>
     *
     * @param message {@link Map} is response message
     * @param entity  {@link Object} is response Dto object
     * @param status  {@link HttpStatus} is http response status
     * @return {@link ResponseEntity}
     */
    public ResponseEntity<Map<String, Object>> responseEntity
    (Map<String, String> message, Object entity, HttpStatus status) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put(Constants.MESSAGE, message);
        response.put(Constants.ENTITY, entity);
        response.put(Constants.ENTITY_LIST, null);
        response.put(Constants.RESPONSE_CODE, status.value());
        return new ResponseEntity<>(response, status);
    }
}
