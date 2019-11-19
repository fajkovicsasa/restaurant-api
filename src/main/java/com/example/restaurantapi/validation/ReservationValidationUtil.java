package com.example.restaurantapi.validation;

import com.example.restaurantapi.constants.ApplicationSettingsConstants;
import com.example.restaurantapi.domain.ApplicationSetting;
import com.example.restaurantapi.domain.Reservation;
import com.example.restaurantapi.service.ApplicationSettingsService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
public class ReservationValidationUtil {

    private final ApplicationSettingsService applicationSettingsService;

    public ReservationValidationUtil(ApplicationSettingsService applicationSettingsService) {
        this.applicationSettingsService = applicationSettingsService;
    }

    public void validateEntity(Reservation reservation) {

        LocalDateTime now = LocalDateTime.now();

        Optional<ApplicationSetting> optional = applicationSettingsService.getSpecificSettingByName(ApplicationSettingsConstants.RESERVATION_DAYS);
        if (optional.isEmpty()) {
            throw new IllegalStateException("No reservation days limit found in the database! Can't validate ");
        }

        int maxDaysForFutureReservations = Integer.parseInt(optional.get().getValue());

        if (DAYS.between(now, reservation.getDateTime()) > maxDaysForFutureReservations) {
            throw new IllegalArgumentException(String.format("Reservation with date %s exceeds the amount of days that are allowed to make a reservation in the future. Max allowed days is %s", reservation.getDateTime(), maxDaysForFutureReservations));
        }
    }

}
