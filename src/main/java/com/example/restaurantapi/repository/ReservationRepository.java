package com.example.restaurantapi.repository;

import com.example.restaurantapi.domain.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends PagingAndSortingRepository<Reservation, Long> {

    @Query(value = "from Reservation where table.id = :tableId and dateTime between :dateTimeFrom  AND :dateTimeUntil")
    List<Reservation> findAllByTableIdAndDateTime_DateEquals(Long tableId, LocalDateTime dateTimeFrom, LocalDateTime dateTimeUntil);

}
