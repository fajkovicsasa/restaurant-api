package com.example.restaurantapi.validation;

import com.example.restaurantapi.constants.ApplicationSettingsConstants;
import com.example.restaurantapi.domain.ApplicationSetting;
import com.example.restaurantapi.domain.FoodCategory;
import com.example.restaurantapi.domain.Reservation;
import com.example.restaurantapi.domain.Table;
import com.example.restaurantapi.exception.InvalidServingTimeException;
import com.example.restaurantapi.exception.NotEnoughSeatAtTheTableException;
import com.example.restaurantapi.service.ApplicationSettingsService;
import com.example.restaurantapi.service.FoodCategoryService;
import com.example.restaurantapi.service.ReservationService;
import com.example.restaurantapi.service.TableService;
import lombok.NonNull;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Component
public class ReservationValidationUtil {

    private final ApplicationSettingsService applicationSettingsService;
    private final FoodCategoryService foodCategoryService;
    private final TableService tableService;
    private final ReservationService reservationService;

    public ReservationValidationUtil(ApplicationSettingsService applicationSettingsService, FoodCategoryService foodCategoryService, TableService tableService, @Lazy ReservationService reservationService) {
        this.applicationSettingsService = applicationSettingsService;
        this.foodCategoryService = foodCategoryService;
        this.tableService = tableService;
        this.reservationService = reservationService;
    }

    public void validateEntity(Reservation reservation) {

        LocalDateTime now = LocalDateTime.now();

        ApplicationSetting applicationSetting = applicationSettingsService.getSpecificSettingByName(ApplicationSettingsConstants.RESERVATION_DAYS);

        int maxDaysForFutureReservations = Integer.parseInt(applicationSetting.getValue());

        if (DAYS.between(now, reservation.getDateTime()) > maxDaysForFutureReservations) {
            throw new IllegalArgumentException(String.format("Reservation with date %s exceeds the amount of days that are allowed to make a reservation in the future. Max allowed days is %s", reservation.getDateTime(), maxDaysForFutureReservations));
        }

        validateServingMealTimes(reservation.getFoodCategories(), reservation.getDateTime().toLocalTime());
        validatePeopleCountForTable(reservation.getTable().getId(), reservation.getPeopleCount(), reservation.getDateTime().toLocalDate());

    }

    public void validateServingMealTimes(@NonNull Set<FoodCategory> foodCategoriesInReservation, @NonNull LocalTime reservationTime) {
        Set<Long> foodCategoryIds = foodCategoriesInReservation.stream().map(i -> i.getId()).collect(Collectors.toSet());
        Set<FoodCategory> foodCategories = foodCategoryService.getFoodCategoriesByIds(foodCategoryIds);

        for (FoodCategory fc : foodCategoriesInReservation) {
            Optional<FoodCategory> optionalTargetCategory = foodCategories.stream().filter(f -> f.getId() == fc.getId()).findFirst();
            if (optionalTargetCategory.isPresent()) {
                if (fc.getServingTimeFrom().isBefore(reservationTime) || fc.getServingTimeUntil().isAfter(reservationTime)) {
                    throw new InvalidServingTimeException(optionalTargetCategory.get(), reservationTime);
                }
            }
        }
    }

    public void validatePeopleCountForTable(@NonNull Long tableId, @NonNull Integer peopleCount, LocalDate date) {
        Table table = tableService.getTable(tableId);
        List<Reservation> reservationsMatchingTableAndDate = reservationService.findByTableAndDate(tableId, date);

        Integer seatsAlreadyReserved = reservationsMatchingTableAndDate.stream().mapToInt(i -> i.getPeopleCount()).sum();
        if (peopleCount > seatsAlreadyReserved) {
            throw new NotEnoughSeatAtTheTableException((table.getCapacity() - seatsAlreadyReserved), peopleCount);
        }

    }

}
