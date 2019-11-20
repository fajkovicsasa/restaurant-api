package com.example.restaurantapi.repository;

import com.example.restaurantapi.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findAllByTableIdAndDateEquals(Long tableId, LocalDate dateTime);

}
