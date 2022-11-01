package com.ideas2it.healthcare.response;

import com.ideas2it.healthcare.common.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuccessResponse {

    public static ResponseEntity<Map<String, Object>> responseEntity
            (String message, Object entity, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put(Constants.MESSAGE, message);
        response.put(Constants.ENTITY, entity);
        response.put(Constants.ENTITY_LIST, null);
        response.put(Constants.RESPONSE_CODE, status.value());
        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<Map<String, Object>> responseEntity
            (String message, List<?> entityList, HttpStatus status, double totalPages) {
        Map<String, Object> response = new HashMap<>();
        response.put(Constants.MESSAGE, message);
        response.put(Constants.ENTITY, null);
        response.put(Constants.ENTITY_LIST, entityList);
        response.put(Constants.RESPONSE_CODE, status.value());
        response.put(Constants.TOTAL_PAGES, totalPages);
        return new ResponseEntity<>(response, status);
    }
}
