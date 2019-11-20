package com.example.restaurantapi.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@javax.persistence.Table(name = "TABLES")
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Min(0)
    private Integer capacity;
    @NotNull
    private Boolean isActive;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "LOCATION_ID")
    private TableLocation tableLocation;

}
