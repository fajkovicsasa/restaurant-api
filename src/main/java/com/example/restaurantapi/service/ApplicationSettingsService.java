package com.example.restaurantapi.service;

import com.example.restaurantapi.domain.ApplicationSetting;
import com.example.restaurantapi.dto.ApplicationSettingDTO;
import com.example.restaurantapi.exception.ApplicationSettingAlreadyExistsException;
import com.example.restaurantapi.exception.ApplicationSettingNotFoundException;
import com.example.restaurantapi.mapper.ApplicationSettingsMapper;
import com.example.restaurantapi.repository.ApplicationSettingsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationSettingsService {

    private ApplicationSettingsRepository applicationSettingsRepository;

    public ApplicationSettingsService(ApplicationSettingsRepository applicationSettingsRepository) {
        this.applicationSettingsRepository = applicationSettingsRepository;
    }

    public List<ApplicationSetting> getAllSettings() {
        return applicationSettingsRepository.findAll();
    }

    //todo remove optional, throw exception
    public Optional<ApplicationSetting> getSpecificSettingById(Long id) {

        return applicationSettingsRepository.findById(id);
    }

    public Optional<ApplicationSetting> getSpecificSettingByName(String name) {
        Optional<ApplicationSetting> optional = applicationSettingsRepository.findByName(name);
        if (optional.isEmpty()) {
            throw new ApplicationSettingNotFoundException(name);
        }
        return optional;
    }

    public void addNewSetting(ApplicationSettingDTO dto) {
        Optional<ApplicationSetting> optional = applicationSettingsRepository.findByName(dto.getName());
        if (optional.isPresent()) {
            throw new ApplicationSettingAlreadyExistsException(dto.getName());
        }
        applicationSettingsRepository.save(ApplicationSettingsMapper.toEntity(dto));
    }

    public void updateSetting(ApplicationSetting setting) {
        if (getSpecificSettingById(setting.getId()).isEmpty()) {
            throw new ApplicationSettingNotFoundException(setting.getId());
        }
        applicationSettingsRepository.save(setting);
    }


}
