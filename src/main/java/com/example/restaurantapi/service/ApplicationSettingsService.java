package com.example.restaurantapi.service;

import com.example.restaurantapi.domain.ApplicationSetting;
import com.example.restaurantapi.exception.ApplicationSettingAlreadyExistsException;
import com.example.restaurantapi.exception.ResourceNotFoundException;
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

    public ApplicationSetting getSpecificSettingById(Long id) {

        Optional<ApplicationSetting> optional = applicationSettingsRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException(id, ApplicationSetting.class);
        }

        return optional.get();
    }

    public ApplicationSetting getSpecificSettingByName(String name) {
        Optional<ApplicationSetting> optional = applicationSettingsRepository.findByName(name);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException(name, ApplicationSetting.class);
        }
        return optional.get();
    }

    public void addNewSetting(ApplicationSetting applicationSetting) {
        Optional<ApplicationSetting> optional = applicationSettingsRepository.findByName(applicationSetting.getName());
        if (optional.isPresent()) {
            throw new ApplicationSettingAlreadyExistsException(applicationSetting.getName());
        }
        applicationSettingsRepository.save(applicationSetting);
    }

    public void updateSetting(ApplicationSetting setting) {
        Optional<ApplicationSetting> optional = applicationSettingsRepository.findById(setting.getId());
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException(setting.getId(), ApplicationSetting.class);
        }


        applicationSettingsRepository.save(setting);
    }

    public void deleteSetting(Long id) {
        applicationSettingsRepository.deleteById(id);
    }
}
