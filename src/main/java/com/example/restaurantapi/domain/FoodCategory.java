package com.example.restaurantapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "FOOD_CATEGORIES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FoodCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private LocalTime servingTimeFrom;
    private LocalTime servingTimeUntil;
    @NotNull
    private Boolean isActive;

    @JsonIgnoreProperties("foodCategories")
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "RESERVATIONS_FOOD_CATEGORIES",
            joinColumns = {@JoinColumn(name = "FOOD_CATEGORY_ID")},
            inverseJoinColumns = {@JoinColumn(name = "RESERVATION_ID")})
    private Set<Reservation> reservations = new HashSet<>();
}
