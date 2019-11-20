package com.example.restaurantapi.controller;

import com.example.restaurantapi.dto.ApplicationSettingDTO;
import com.example.restaurantapi.exception.ApplicationSettingAlreadyExistsException;
import com.example.restaurantapi.exception.ResourceNotFoundException;
import com.example.restaurantapi.mapper.ApplicationSettingsMapper;
import com.example.restaurantapi.service.ApplicationSettingsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/settings")
public class ApplicationSettingsController {

    private ApplicationSettingsService applicationSettingsService;

    public ApplicationSettingsController(ApplicationSettingsService applicationSettingsService) {
        this.applicationSettingsService = applicationSettingsService;
    }

    @GetMapping
    public ResponseEntity getAllSettings() {
        return new ResponseEntity(this.applicationSettingsService.getAllSettings(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getSpecificSetting(@PathVariable Long id) {
        try {
            return new ResponseEntity(applicationSettingsService.getSpecificSettingById(id), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity addNewSetting(@RequestBody ApplicationSettingDTO dto) {
        try {
            applicationSettingsService.addNewSetting(ApplicationSettingsMapper.toEntity(dto));
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (ApplicationSettingAlreadyExistsException e) {
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PutMapping
    public ResponseEntity updateSetting(@RequestBody ApplicationSettingDTO dto) {
        applicationSettingsService.updateSetting(ApplicationSettingsMapper.toEntity(dto));
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSetting(@PathVariable Long id) {
        applicationSettingsService.deleteSetting(id);
        return new ResponseEntity(HttpStatus.OK);
    }


}
