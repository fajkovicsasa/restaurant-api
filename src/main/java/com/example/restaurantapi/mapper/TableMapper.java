package com.example.restaurantapi.mapper;

import com.example.restaurantapi.domain.Table;
import com.example.restaurantapi.domain.TableLocation;
import com.example.restaurantapi.dto.TableDTO;

public abstract class TableMapper {

    public static TableDTO toDTO(Table table) {
        return new TableDTO(table.getId(), table.getCapacity(), table.getTableLocation().getId(), table.getIsActive());
    }

    public static Table toEntity(TableDTO dto) {
        TableLocation tableLocation = new TableLocation();
        tableLocation.setId(dto.getLocationId());
        return new Table(dto.getId(), dto.getCapacity(), dto.getIsActive(), tableLocation);
    }
}
