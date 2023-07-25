package com.project.service;

import com.project.entity.User;
import com.project.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository repository;

    public LoginService(PasswordEncoder passwordEncoder, UserRepository repository) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
    }

    public Optional<User> findById(String id) {
        return repository.findById(id);
    }

    public Optional<User> findByAccountNumber(String accountNumber) {

        return repository.findByAccountNumber(accountNumber);
    }

    public boolean matchPassword(String password, String encodedPassword) {

        return passwordEncoder.matches(password, encodedPassword);
    }
}
