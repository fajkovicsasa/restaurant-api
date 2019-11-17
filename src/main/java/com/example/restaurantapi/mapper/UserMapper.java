package com.example.restaurantapi.mapper;

import com.example.restaurantapi.domain.User;
import com.example.restaurantapi.domain.UserRegistrationDTO;
import com.example.restaurantapi.dto.UserDTO;

public abstract class UserMapper {

    public static User registrationDTOToUser(UserRegistrationDTO registrationDTO) {
        User user = new User();
        user.setUsername(registrationDTO.getUsername());
        user.setPassword(registrationDTO.getPassword());
        user.setIsActive(true);

        return user;
    }

    public static UserDTO toUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setIsActive(user.getIsActive());

        return dto;
    }

}
