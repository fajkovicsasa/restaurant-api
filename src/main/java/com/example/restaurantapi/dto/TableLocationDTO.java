package com.example.restaurantapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TableLocationDTO {

    private Long id;
    private String name;
    private Long parentId;
    private Boolean isActive;
}
