package com.example.restaurantapi.exception;

import lombok.Getter;

@Getter
public class ApplicationSettingNotFoundException extends RuntimeException {
    private String message;

    public ApplicationSettingNotFoundException(Long id) {
        this.message = String.format("Application setting with id %s not found.", id);
    }

        public ApplicationSettingNotFoundException(String name) {
        this.message = String.format("Application setting with name %s not found.", name);
    }


}
