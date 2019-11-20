package com.example.restaurantapi.service;

import com.example.restaurantapi.domain.TableLocation;
import com.example.restaurantapi.exception.ResourceNotFoundException;
import com.example.restaurantapi.repository.TableLocationsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableLocationService {

    private TableLocationsRepository tableLocationsRepository;

    public TableLocationService(TableLocationsRepository tableLocationsRepository) {
        this.tableLocationsRepository = tableLocationsRepository;
    }

    public List<TableLocation> getAllLocations() {
        return tableLocationsRepository.findAll();
    }

    public TableLocation getTableLocation(Long id) {
        Optional<TableLocation> optional = tableLocationsRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException(id, TableLocation.class);
        }

        return optional.get();
    }

    public List<TableLocation> getAllActiveLocations() {
        return tableLocationsRepository.findTableLocationByIsActiveTrue();
    }

    public void updateTableLocation(TableLocation tableLocation) {
        if (tableLocation.getId() == null) {
            throw new IllegalArgumentException("Id must be null when changing the table location.");
        }

        if (tableLocationsRepository.findById(tableLocation.getId()).isEmpty()) {
            throw new ResourceNotFoundException(tableLocation.getId(), TableLocation.class);
        }

        tableLocationsRepository.save(tableLocation);
    }

    public void addTableLocation(TableLocation tableLocation) {
        if (tableLocation.getId() != null) {
            throw new IllegalArgumentException("Id must be null when creating a new table location ");
        }

        tableLocationsRepository.save(tableLocation);
    }

    public void deactivateLocation(Long id) {
        Optional<TableLocation> optional = tableLocationsRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException(id, TableLocation.class);
        }
        tableLocationsRepository.deactivate(id);
    }


    public void activateLocation(Long id) {
        Optional<TableLocation> optional = tableLocationsRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException(id, TableLocation.class);
        }
        tableLocationsRepository.activate(id);
    }
}
