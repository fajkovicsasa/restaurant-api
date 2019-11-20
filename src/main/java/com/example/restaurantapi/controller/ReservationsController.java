package com.example.restaurantapi.controller;

import com.example.restaurantapi.dto.ReservationDTO;
import com.example.restaurantapi.exception.ResourceNotFoundException;
import com.example.restaurantapi.mapper.ReservationsMapper;
import com.example.restaurantapi.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationsController {

    private ReservationService reservationService;

    public ReservationsController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity getAllReservations() {
        return new ResponseEntity(reservationService.getAllReservations(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getReservation(@PathVariable Long id) {
        try {
            return new ResponseEntity(reservationService.getReservation(id), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping //fixme - not saving food categories
    public ResponseEntity createReservation(@RequestBody ReservationDTO dto) {
        try {
            reservationService.addNewReservation(ReservationsMapper.toEntity(dto));
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping //fixme - not saving food categories
    public ResponseEntity updateReservation(@RequestBody ReservationDTO dto) {
        try {
            reservationService.updateReservation(ReservationsMapper.toEntity(dto));
            return new ResponseEntity(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity(HttpStatus.OK);
    }


}
