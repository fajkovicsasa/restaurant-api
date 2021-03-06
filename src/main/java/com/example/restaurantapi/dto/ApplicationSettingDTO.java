package com.example.restaurantapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationSettingDTO {
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String value;
}
