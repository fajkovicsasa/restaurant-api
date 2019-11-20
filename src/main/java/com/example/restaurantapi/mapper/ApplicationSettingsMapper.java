package com.example.restaurantapi.mapper;

import com.example.restaurantapi.domain.ApplicationSetting;
import com.example.restaurantapi.dto.ApplicationSettingDTO;

public abstract class ApplicationSettingsMapper {

    public static ApplicationSetting toEntity(ApplicationSettingDTO dto) {
        return new ApplicationSetting(dto.getId(), dto.getName(), dto.getValue());
    }

    public static ApplicationSettingDTO toDTO(ApplicationSetting applicationSetting) {
        return new ApplicationSettingDTO(applicationSetting.getId(), applicationSetting.getName(), applicationSetting.getValue());
    }
}
