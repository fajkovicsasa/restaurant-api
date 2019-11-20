package com.example.restaurantapi.mapper;

import com.example.restaurantapi.domain.FoodCategory;
import com.example.restaurantapi.domain.Reservation;
import com.example.restaurantapi.dto.FoodCategoryDTO;

import java.util.HashSet;
import java.util.Set;

public abstract class FoodCategoryMapper {
    public static FoodCategoryDTO toDTO(FoodCategory foodCategory) {

        HashSet<Long> reservationIds = new HashSet<>();

        for (Reservation r : foodCategory.getReservations()) {
            reservationIds.add(r.getId());
        }

        return new FoodCategoryDTO(foodCategory.getId(), foodCategory.getName(), foodCategory.getServingTimeFrom(), foodCategory.getServingTimeUntil(), foodCategory.getIsActive(), reservationIds);
    }

    public static FoodCategory toEntity(FoodCategoryDTO dto) {
        Set<Reservation> reservations = new HashSet<>();

        for (Long l : dto.getReservations()) {
            Reservation r = new Reservation();
            r.setId(l);
            reservations.add(r);
        }

        return new FoodCategory(dto.getId(), dto.getName(), dto.getServingTimeFrom(), dto.getServingTimeUntil(), dto.getIsActive(), reservations);
    }
}
