package com.example.restaurantapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Table;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "RESERVATIONS")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer peopleCount;
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "TABLE_ID")
    private com.example.restaurantapi.domain.Table table;

    @JsonIgnoreProperties("reservations")
    @ManyToMany(mappedBy = "reservations")
    private Set<FoodCategory> foodCategories = new HashSet<>();

}
