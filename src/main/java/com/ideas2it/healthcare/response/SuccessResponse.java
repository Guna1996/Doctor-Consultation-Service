package com.ideas2it.healthcare.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuccessResponse {

    public static ResponseEntity<Map<String, Object>> responseEntity(String message, Object entity, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("entity", entity);
        response.put("entityList", null);
        response.put("responseCode", status.value());

        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<Map<String, Object>> responseEntity(String message, List<?> entityList, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("entity", null);
        response.put("entityList", entityList);
        response.put("responseCode", status.value());

        return new ResponseEntity<>(response, status);
    }
}
