package com.ideas2it.healthcare.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class HelpEntity/*<T> extends HttpEntity<T>*/ {

    private String message;
    private Object entity;

    //private List<Object> entityList;
    private int responseCode;

    public HelpEntity(String message, Object entity, HttpStatus responseCode) {
        this.message = message;
        this.entity = entity;
        this.responseCode = responseCode.value();
    }

    /*public ResponseEntity(String message, List<Object> entityList, HttpStatus responseCode) {
        this.message = message;
        this.entityList = entityList;
        this.responseCode = responseCode.value();
    }*/
}