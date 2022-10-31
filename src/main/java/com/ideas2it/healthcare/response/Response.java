package com.ideas2it.healthcare.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class Response {

    public static ResponseEntity<Map<String, Object>> responseEntity(String message,
                                                                     Object entity,
                                                                     HttpStatus status) {
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("message", message);
        response.put("entity", entity);
        response.put("responseCode", status.value());

        return new ResponseEntity<>(response, status);
    }
}
