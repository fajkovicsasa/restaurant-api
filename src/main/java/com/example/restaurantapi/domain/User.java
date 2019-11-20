package com.example.restaurantapi.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "USERS")
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean isActive;
    @Size(min = 4, max = 50, message = "Username must contain at least 4 characters and a max of 50 characters.")
    private String username;
    @Size(min = 4, max = 100, message = "Password must contain at least 4 characters and a max of 100 characters.")
    private String password;
}
