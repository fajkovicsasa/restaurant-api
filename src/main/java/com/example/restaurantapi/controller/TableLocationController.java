package com.example.restaurantapi.controller;

import com.example.restaurantapi.dto.TableLocationDTO;
import com.example.restaurantapi.exception.ResourceNotFoundException;
import com.example.restaurantapi.mapper.TableLocationMapper;
import com.example.restaurantapi.service.TableLocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/table-locations")
public class TableLocationController {

    private TableLocationService tableLocationService;

    public TableLocationController(TableLocationService tableLocationService) {
        this.tableLocationService = tableLocationService;
    }

    @GetMapping
    public ResponseEntity getAllTableLocations() {
        return new ResponseEntity(tableLocationService.getAllLocations(), HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity getAllActiveLocations() {
        return new ResponseEntity(tableLocationService.getAllActiveLocations(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getTableLocation(@PathVariable Long id) {
        try {
            return new ResponseEntity(tableLocationService.getTableLocation(id), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity updateTableLocation(@RequestBody TableLocationDTO dto) {
        try {
            tableLocationService.updateTableLocation(TableLocationMapper.toEntity(dto));
            return new ResponseEntity(HttpStatus.OK);
        } catch (IllegalArgumentException | ResourceNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity createTableLocation(@RequestBody TableLocationDTO dto) {
        try {
            tableLocationService.addTableLocation(TableLocationMapper.toEntity(dto));
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity deactivateTableLocation(@PathVariable Long id) {
        try {
            tableLocationService.deactivateLocation(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity activateTableLocation(@PathVariable Long id) {
        try {
            tableLocationService.activateLocation(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
