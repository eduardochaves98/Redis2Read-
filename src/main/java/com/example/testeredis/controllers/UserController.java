package com.example.testeredis.controllers;

import com.example.testeredis.models.User;
import com.example.testeredis.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/all")
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping
    public Iterable<User> all(@RequestParam(defaultValue = "") String email) {
        if (email.isEmpty()) {
            return userRepository.findAll();
        } else {
            Optional<User> user = Optional.ofNullable(userRepository.findFirstByEmail(email));
            return user.map(List::of).orElse(Collections.emptyList());
        }
    }
}
