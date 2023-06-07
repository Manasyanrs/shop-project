package com.example.myshop.service.impl;

import com.example.myshop.entity.User;
import com.example.myshop.repository.UserRepository;
import com.example.myshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }


}
