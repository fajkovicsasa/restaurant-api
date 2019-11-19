package com.example.restaurantapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ApplicationSettingDTO {
    @NotNull
    private String name;
    @NotNull
    private String value;
}
