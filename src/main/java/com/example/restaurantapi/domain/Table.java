package com.example.restaurantapi.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;

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

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "LOCATION_ID")
    private TableLocation tableLocation;

}
