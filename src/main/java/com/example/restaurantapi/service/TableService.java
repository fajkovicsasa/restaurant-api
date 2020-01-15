package com.example.restaurantapi.service;

import com.example.restaurantapi.domain.Table;
import com.example.restaurantapi.dto.TableDTO;
import com.example.restaurantapi.exception.ResourceNotFoundException;
import com.example.restaurantapi.mapper.TableMapper;
import com.example.restaurantapi.repository.TableRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableService {

    private final TableRepository tableRepository;

    public TableService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public List<Table> getAllTables() {
        return tableRepository.findAll();
    }

    public List<Table> getAllActiveTables() {
        return tableRepository.findTableByIsActiveTrue();
    }

    public Table getTable(Long id) {
        Optional<Table> optional = tableRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException(id, Table.class);
        }
        return optional.get();
    }

    public void createTable(TableDTO dto) {
        if (dto.getId() != null) {
            throw new IllegalArgumentException("Table id must be null when creating table.");
        }

        tableRepository.save(TableMapper.toEntity(dto));
    }

    public void updateTable(TableDTO dto) {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("Id must not be null when updating table entity.");
        }

        Optional<Table> optional = tableRepository.findById(dto.getId());
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException(dto.getId(), Table.class);
        }

        tableRepository.save(TableMapper.toEntity(dto));
    }

    public void deactivateTable(Long id) {
        Optional<Table> optional = tableRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException(id, Table.class);
        }
        tableRepository.deactivate(id);
    }

    public void activateTable(Long id) {
        Optional<Table> optional = tableRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException(id, Table.class);
        }
        tableRepository.activate(id);
    }
}
