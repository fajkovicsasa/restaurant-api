package com.example.restaurantapi.mapper;

import com.example.restaurantapi.domain.Reservation;
import com.example.restaurantapi.domain.Table;
import com.example.restaurantapi.dto.ReservationDTO;

public abstract class ReservationsMapper {

    public static Reservation toEntity(ReservationDTO dto) {
        Table table = new Table();
        table.setId(dto.getTableId());
        return new Reservation(dto.getId(), dto.getPeopleCount(), dto.getDateTime(), table);
    }

    public static ReservationDTO toDTO(Reservation reservation) {
        return new ReservationDTO(reservation.getId(), reservation.getPeopleCount(), reservation.getDateTime(), reservation.getTable().getId());
    }
}
