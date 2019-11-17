package com.example.restaurantapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRegistrationDTO {
    @Size(min = 4, max = 50, message = "Username must contain between 4 and 50 characters.")
    private String username;
    @Size(min = 4, max = 100, message = "Password must contain between 4 and 100 characters.")
    private String password;


}
