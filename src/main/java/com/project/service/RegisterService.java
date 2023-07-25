package com.project.service;

import com.project.entity.User;
import com.project.exception.CustomException;
import com.project.exception.UserException;
import com.project.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class RegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(String accountNumber, String name, String surname, String email,
                       String phoneNumber, String password, String confirmPassword) throws CustomException {

        if (Objects.isNull(accountNumber)) {
            throw UserException.accountNumberNull();
        }

        if (Objects.isNull(name)) {
            throw UserException.nameNull();
        }

        if (Objects.isNull(email)) {
            throw UserException.emailNull();
        }

        if (Objects.isNull(phoneNumber)) {
            throw UserException.phoneNumberNull();
        }

        if (Objects.isNull(password)) {
            throw UserException.passwordNull();
        }


        if (userRepository.existsByAccountNumber(accountNumber)) {
            throw UserException.duplicatedAccountNumber();
        }

        if (!password.equals(confirmPassword)) {
            throw UserException.passwordNotMatch();
        }


        User user = new User();

        user.setAccountNumber(accountNumber);
        user.setName(name + " " + surname);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(passwordEncoder.encode(password));
        user.setVerification(false);

        return userRepository.save(user);
    }

    public void setVerify(String accountNumber) throws CustomException {
        Optional<User> opt = userRepository.findByAccountNumber(accountNumber);

        if(opt.isEmpty()) {
            throw UserException.userNotFound();
        }

        User user = opt.get();

        user.setVerification(true);

        userRepository.save(user);
    }
}
