package com.example.restaurantapi.controller;

import com.example.restaurantapi.domain.ApplicationSetting;
import com.example.restaurantapi.dto.ApplicationSettingDTO;
import com.example.restaurantapi.service.ApplicationSettingsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/settings")
public class ApplicationSettingsController {

    private ApplicationSettingsService applicationSettingsService;

    public ApplicationSettingsController(ApplicationSettingsService applicationSettingsService) {
        this.applicationSettingsService = applicationSettingsService;
    }

    @GetMapping
    public ResponseEntity<List<ApplicationSetting>> getAllSettings() {
        return new ResponseEntity(this.applicationSettingsService.getAllSettings(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationSetting> getSpecificSetting(@PathVariable Long id) {
        Optional<ApplicationSetting> optional = applicationSettingsService.getSpecificSettingById(id);
        return optional.isEmpty() ? new ResponseEntity(HttpStatus.NOT_FOUND) : new ResponseEntity<>(optional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addNewSetting(@RequestBody ApplicationSettingDTO dto) {
        applicationSettingsService.addNewSetting(dto);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity updateSetting(@RequestBody ApplicationSetting setting) {
        applicationSettingsService.updateSetting(setting);
        return new ResponseEntity(HttpStatus.OK);
    }


}
