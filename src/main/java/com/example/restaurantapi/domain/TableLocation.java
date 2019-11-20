package com.example.restaurantapi.domain;

import lombok.*;

import javax.persistence.Table;
import javax.persistence.*;

@Entity
@Table(name = "TABLE_LOCATIONS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TableLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long parentId;
    private Boolean isActive;

}
