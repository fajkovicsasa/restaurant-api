package com.example.restaurantapi.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    private String message;

    public ResourceNotFoundException(Long id, Class clazz) {
        this.message = String.format("Resource of class %s and id %s not found.", clazz, id);
    }

    public ResourceNotFoundException(String string, Class clazz) {
        this.message = String.format("Resource of class %s and queried value %s not found.", clazz, string);
    }

    public ResourceNotFoundException(Long id, Class clazz, String additionalMessage) {
        this.message = String.format("Resource of class %s and id %s not found. Additional information: %s", clazz, id, additionalMessage);
    }
}
