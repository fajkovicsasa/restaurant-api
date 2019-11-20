package com.example.restaurantapi.service;

import com.example.restaurantapi.domain.Reservation;
import com.example.restaurantapi.exception.ResourceNotFoundException;
import com.example.restaurantapi.repository.ReservationRepository;
import com.example.restaurantapi.validation.ReservationValidationUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationValidationUtil reservationValidationUtil;

    public ReservationService(ReservationRepository reservationRepository, ReservationValidationUtil reservationValidationUtil) {
        this.reservationRepository = reservationRepository;
        this.reservationValidationUtil = reservationValidationUtil;
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation getReservation(Long id) {
        Optional<Reservation> optional = reservationRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException(id, Reservation.class);
        }
        return optional.get();
    }

    public void addNewReservation(Reservation reservation) {
        if (reservation.getId() != null) {
            throw new IllegalArgumentException("Reservation Id must be null when saving a new reservation.");
        }
        reservationValidationUtil.validateEntity(reservation);

        reservationRepository.save(reservation);
    }

    public void updateReservation(Reservation reservation) {
        if (reservation.getId() == null) {
            throw new IllegalArgumentException("Reservation Id must be defined when updating the reservation.");
        }

        Optional<Reservation> optional = reservationRepository.findById(reservation.getId());
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException(reservation.getId(), Reservation.class);
        }
        reservationValidationUtil.validateEntity(reservation);

        reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    public List<Reservation> findByTableAndDate(Long tableId, LocalDate date) {
        LocalDateTime dateTimeFrom = date.atTime(0,0,0);
        LocalDateTime dateTimeUntil = date.atTime(23,59,59);
        return reservationRepository.findAllByTableIdAndDateTime_DateEquals(tableId, dateTimeFrom, dateTimeUntil);


    }
}
