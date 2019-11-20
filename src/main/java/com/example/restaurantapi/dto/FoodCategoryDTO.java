package com.example.restaurantapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodCategoryDTO {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Time servingTimeFrom;
    private Time servingTimeUntil;
    @NotNull
    private Boolean isActive;
    private Set<Long> reservations;
}
