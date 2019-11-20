package com.example.restaurantapi.controller;

import com.example.restaurantapi.dto.TableDTO;
import com.example.restaurantapi.exception.ResourceNotFoundException;
import com.example.restaurantapi.mapper.TableMapper;
import com.example.restaurantapi.service.TableService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tables")
public class TablesController {

    private TableService tableService;

    public TablesController(TableService tableService) {
        this.tableService = tableService;
    }

    @GetMapping
    public ResponseEntity getAllTables() {
        return new ResponseEntity(tableService.getAllTables(), HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity getActiveTables() {
        return new ResponseEntity(tableService.getAllActiveTables(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getTable(@PathVariable Long id) {
        try {
            return new ResponseEntity(TableMapper.toDTO(tableService.getTable(id)), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity createTable(@RequestBody TableDTO dto) {
        try {
            tableService.createTable(dto);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity updateTable(@RequestBody TableDTO dto) {
        try {
            tableService.updateTable(dto);
            return new ResponseEntity(HttpStatus.OK);
        } catch (ResourceNotFoundException | IllegalArgumentException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}/deactivate")
    public ResponseEntity deactivateTable (@PathVariable Long id) {
        try {
            tableService.deactivateTable(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}/activate")
    public ResponseEntity activateTable (@PathVariable Long id) {
        try {
            tableService.activateTable(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
