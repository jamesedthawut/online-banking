package com.project.repository;

import com.project.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findById(String id);

    Optional<User> findByAccountNumber(String accountNumber);

    Optional<User> findByEmail(String email);

    Optional<User> findByPhoneNumber(String phoneNumber);

    boolean existsByAccountNumber(String accountNumber);

    boolean existsByEmail(String email);
}
