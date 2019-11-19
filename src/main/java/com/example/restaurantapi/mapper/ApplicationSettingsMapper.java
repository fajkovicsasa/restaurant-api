package com.example.restaurantapi.mapper;

import com.example.restaurantapi.domain.ApplicationSetting;
import com.example.restaurantapi.dto.ApplicationSettingDTO;

public abstract class ApplicationSettingsMapper {

    public static ApplicationSetting toEntity(ApplicationSettingDTO dto) {
        return new ApplicationSetting(null, dto.getName(), dto.getValue());
    }
}
