package com.example.restaurantapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TableDTO {
    private Long id;
    @Min(0)
    private Integer capacity;
    @NotNull
    private Long locationId;
    @NotNull
    private Boolean isActive;
}
