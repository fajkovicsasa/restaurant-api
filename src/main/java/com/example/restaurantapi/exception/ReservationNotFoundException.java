package com.example.restaurantapi.exception;

import lombok.Getter;

@Getter
public class ReservationNotFoundException extends RuntimeException {
    private String message;

    public ReservationNotFoundException(Long id) {
        this.message = String.format("Reservation with id %s not found.", id);
    }
}
