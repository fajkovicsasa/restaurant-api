package com.example.restaurantapi.service;

import com.example.restaurantapi.domain.User;
import com.example.restaurantapi.dto.UserDTO;
import com.example.restaurantapi.exception.UserNotFoundException;
import com.example.restaurantapi.mapper.UserMapper;
import com.example.restaurantapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final static Logger log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Long id) throws UserNotFoundException {
        log.info("Retrieving user with id {}", id);
        Optional<User> optionalUser = userRepository.findById(id);

        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException(id);
        }
        return optionalUser.get();
    }

    public void addNewUser(User user) {
        log.info("Adding new user {}", user);
        userRepository.save(user);
    }

    public UserDTO getUserDTO(Long userId) {
        log.info("Retrieving userDto for id {}", userId);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException(userId);
        }

        return UserMapper.toUserDTO(optionalUser.get());
    }
}
