package com.example.myshop.service.impl;

import com.example.myshop.entity.User;
import com.example.myshop.repository.UserRepository;
import com.example.myshop.security.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byEmail = userRepository.findUserByEmail(username);
        if (byEmail.isEmpty()) {
            throw new UsernameNotFoundException("User does not exists");
        }

        return new CurrentUser(byEmail.get());
    }
}
