package com.example.restaurantapi.service;

import com.example.restaurantapi.domain.User;
import com.example.restaurantapi.domain.UserPrincipal;
import com.example.restaurantapi.exception.ResourceNotFoundException;
import com.example.restaurantapi.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> optional = this.userRepository.findByUsername(s);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException(s, User.class);
        }

        return new UserPrincipal(optional.get());
    }
}
