package com.example.restaurantapi.mapper;

import com.example.restaurantapi.domain.TableLocation;
import com.example.restaurantapi.dto.TableLocationDTO;

public abstract class TableLocationMapper {

    public static TableLocation toEntity(TableLocationDTO dto) {
        return new TableLocation(dto.getId(), dto.getName(), dto.getParentId(), dto.getIsActive());
    }

    public static TableLocationDTO toDTO(TableLocation tableLocation) {
        return new TableLocationDTO(tableLocation.getId(), tableLocation.getName(), tableLocation.getParentId(), tableLocation.getIsActive());
    }
}
