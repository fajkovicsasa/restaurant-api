package com.example.restaurantapi.repository;

import com.example.restaurantapi.domain.ApplicationSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationSettingsRepository extends JpaRepository<ApplicationSetting, Long> {

    Optional<ApplicationSetting> findByName(String name);
}
