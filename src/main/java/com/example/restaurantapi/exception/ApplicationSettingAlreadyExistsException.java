package com.example.restaurantapi.exception;

import lombok.Getter;

@Getter
public class ApplicationSettingAlreadyExistsException extends RuntimeException {

    private String message;

    public ApplicationSettingAlreadyExistsException(String settingName) {
        this.message = String.format("Application setting with name %s already exists.", settingName);
    }
}
