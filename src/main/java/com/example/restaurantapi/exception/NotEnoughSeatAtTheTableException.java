package com.example.restaurantapi.exception;

import lombok.Getter;

@Getter
public class NotEnoughSeatAtTheTableException extends  RuntimeException {
    private String message;

    public NotEnoughSeatAtTheTableException(Integer availableSeats, Integer requestedSeats) {
        message = String.format("Not enough room for all people. There are %s available seats but the request was for %s", availableSeats, requestedSeats);
    }
}
