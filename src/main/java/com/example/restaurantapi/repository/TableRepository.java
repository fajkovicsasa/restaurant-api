package com.example.restaurantapi.repository;

import com.example.restaurantapi.domain.Table;
import com.example.restaurantapi.domain.TableLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<Table, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE Table SET isActive = false where id = :id")
    void deactivate(Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Table SET isActive = true where id = :id")
    void activate(Long id);

    List<Table> findTableByIsActiveTrue();

}
