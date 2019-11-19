package com.example.restaurantapi.domain;

import lombok.*;

import javax.persistence.Table;
import javax.persistence.*;
import java.time.LocalDateTime;

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

}
