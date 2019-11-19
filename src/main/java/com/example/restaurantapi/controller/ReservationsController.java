package com.example.restaurantapi.controller;

import com.example.restaurantapi.domain.Reservation;
import com.example.restaurantapi.dto.ReservationDTO;
import com.example.restaurantapi.exception.ReservationNotFoundException;
import com.example.restaurantapi.mapper.ReservationsMapper;
import com.example.restaurantapi.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationsController {

    private ReservationService reservationService;

    public ReservationsController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return new ResponseEntity<>(reservationService.getAllReservations(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable Long id) {
        try {
            return new ResponseEntity(reservationService.getReservation(id), HttpStatus.OK);
        } catch (ReservationNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity createReservation(@RequestBody ReservationDTO dto) {
        try {
            reservationService.addNewReservation(ReservationsMapper.toEntity(dto));
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity updateReservation(@RequestBody ReservationDTO dto) {
        try {
            reservationService.updateReservation(ReservationsMapper.toEntity(dto));
            return new ResponseEntity(HttpStatus.OK);
        } catch (ReservationNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity deleteReservation(Long id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity(HttpStatus.OK);
    }


}
