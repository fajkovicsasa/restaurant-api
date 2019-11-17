package com.example.restaurantapi.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    private String message;

    public UserNotFoundException(Long userId) {
        message = String.format("User with id %s not found in the database", userId);
    }
}
