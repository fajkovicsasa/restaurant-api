package com.example.restaurantapi.exception;

import com.example.restaurantapi.domain.FoodCategory;
import lombok.Getter;

import java.time.LocalTime;

@Getter
public class InvalidServingTimeException extends RuntimeException {

    private String message;

    public InvalidServingTimeException(FoodCategory foodCategory, LocalTime requestedTIme) {
        message = String.format("%s - %s is allowed to be server between %s and %s but the reservation was made for was for %s",
                foodCategory.getId(), foodCategory.getName(), foodCategory.getServingTimeFrom(), foodCategory.getServingTimeUntil(),
                requestedTIme);
    }
}
