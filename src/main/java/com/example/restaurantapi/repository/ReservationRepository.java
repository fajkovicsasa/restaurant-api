package com.example.restaurantapi.repository;

import com.example.restaurantapi.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {


}
