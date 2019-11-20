package com.example.restaurantapi.service;

import com.example.restaurantapi.domain.FoodCategory;
import com.example.restaurantapi.exception.ResourceNotFoundException;
import com.example.restaurantapi.repository.FoodCategoryRepository;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class FoodCategoryService {

    private FoodCategoryRepository foodCategoryRepository;

    public FoodCategoryService(FoodCategoryRepository foodCategoryRepository) {
        this.foodCategoryRepository = foodCategoryRepository;
    }

    public List<FoodCategory> getAllCategories() {
        return foodCategoryRepository.findAll();
    }

    public FoodCategory getFoodCategory(Long id) {
        Optional<FoodCategory> optional = foodCategoryRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException(id, FoodCategory.class);
        }
        return optional.get();
    }

    public Set<FoodCategory> getFoodCategoriesByIds(Set<Long> ids) {
        return foodCategoryRepository.findAllByIdIn(ids);
    }

    public void addNewCategory(FoodCategory foodCategory) {
        if (foodCategory.getId() != null) {
            throw new IllegalArgumentException("Id must be null when adding a new food category.");
        }
        foodCategoryRepository.save(foodCategory);
    }

    public void updateCategory(FoodCategory foodCategory) {
        if (foodCategory.getId() == null) {
            throw new IllegalArgumentException("Id must not be null when updating food category.");
        }
        foodCategoryRepository.save(foodCategory);
    }

    public void activateCategory(Long id) {
        foodCategoryRepository.activate(id);
    }

    public void deactivateCategory(Long id) {
        foodCategoryRepository.deactivate(id);
    }
}
