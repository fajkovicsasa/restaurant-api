package com.example.restaurantapi.controller;

import com.example.restaurantapi.domain.UserRegistrationDTO;
import com.example.restaurantapi.exception.ResourceNotFoundException;
import com.example.restaurantapi.mapper.UserMapper;
import com.example.restaurantapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final static Logger log = LoggerFactory.getLogger(UserController.class);
    private UserService userService;
    private PasswordEncoder passwordEncoder;


    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping()
    public ResponseEntity registerUser(@RequestBody @Valid UserRegistrationDTO userRegistrationDTO, BindingResult bindingResult) {
        log.info("Registering new user {}", userRegistrationDTO);

        if (bindingResult.hasErrors()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        userRegistrationDTO.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

        userService.addNewUser(UserMapper.registrationDTOToUser(userRegistrationDTO));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getUserDetails(@PathVariable Long id) {
        try {
            return new ResponseEntity(UserMapper.toUserDTO(userService.getUser(id)), HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
