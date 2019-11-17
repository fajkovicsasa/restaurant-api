package com.example.restaurantapi.exception;

import com.example.restaurantapi.domain.User;
import lombok.Getter;

@Getter
public class UserNotActiveException extends RuntimeException {
    private String message;

    public UserNotActiveException(User user) {
        message = String.format("User %s with id %s is not active.", user.getUsername(), user.getId());
    }
}
