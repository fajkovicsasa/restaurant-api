package com.example.restaurantapi.mapper;

import com.example.restaurantapi.domain.FoodCategory;
import com.example.restaurantapi.domain.Reservation;
import com.example.restaurantapi.domain.Table;
import com.example.restaurantapi.dto.ReservationDTO;

import java.util.HashSet;

public abstract class ReservationsMapper {

    public static Reservation toEntity(ReservationDTO dto) {
        Table table = new Table();
        table.setId(dto.getTableId());

        HashSet<FoodCategory> foodCategories = new HashSet<>();

        if (dto.getFoodCategories() != null) { // In case the customer has no idea what they will eat
            for (Long l : dto.getFoodCategories()) {
                FoodCategory foodCategory = new FoodCategory();
                foodCategory.setId(l);
                foodCategories.add(foodCategory);
            }
        }

        return new Reservation(dto.getId(), dto.getPeopleCount(), dto.getDateTime(), table, foodCategories);
    }

    public static ReservationDTO toDTO(Reservation reservation) {

        HashSet<Long> foodCategories = new HashSet<>();

        for (FoodCategory fc : reservation.getFoodCategories()) {
            foodCategories.add(fc.getId());
        }

        return new ReservationDTO(reservation.getId(), reservation.getPeopleCount(), reservation.getDateTime(), reservation.getTable().getId(), foodCategories);
    }
}
