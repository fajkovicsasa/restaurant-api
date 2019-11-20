package com.example.restaurantapi.service;

import com.example.restaurantapi.domain.User;
import com.example.restaurantapi.exception.ResourceNotFoundException;
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

    public User getUser(Long id) {
        log.info("Retrieving user with id {}", id);
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException(id, User.class);
        }

        return optionalUser.get();
    }

    public void addNewUser(User user) {
        log.info("Adding new user {}", user);
        userRepository.save(user);
    }
}
