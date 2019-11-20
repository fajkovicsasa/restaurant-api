package com.example.restaurantapi.repository;

import com.example.restaurantapi.domain.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE FoodCategory SET isActive = true where id = :id")
    void activate(Long id);


    @Transactional
    @Modifying
    @Query("UPDATE FoodCategory  set isActive = false where id = :id")
    void deactivate(Long id);
}
