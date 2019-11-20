package com.example.restaurantapi.repository;

import com.example.restaurantapi.domain.TableLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TableLocationsRepository extends JpaRepository<TableLocation, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE TableLocation set isActive = false where  id = :id")
    void deactivate(Long id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE TableLocation set isActive = true where id = :id")
     void activate(Long id);

    List<TableLocation> findTableLocationByIsActiveTrue();
}
