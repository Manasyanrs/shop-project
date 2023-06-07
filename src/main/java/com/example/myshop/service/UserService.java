package com.example.myshop.service;

import com.example.myshop.entity.User;

import java.util.Optional;

public interface UserService {
    void save(User user);

    Optional<User> getUserByEmail(String email);
}
