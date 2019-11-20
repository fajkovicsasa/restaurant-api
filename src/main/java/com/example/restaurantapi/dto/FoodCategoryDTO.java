package com.example.restaurantapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodCategoryDTO {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private LocalTime servingTimeFrom;
    private LocalTime servingTimeUntil;
    @NotNull
    private Boolean isActive;
    private Set<Long> reservations;
}
