package com.example.restaurantapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {

    private Long id;
    @NotNull @Min(1)
    private Integer peopleCount;
    @NotNull
    private LocalDateTime dateTime;
    @NotNull
    private Long tableId;
}
