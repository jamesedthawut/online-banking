package com.project.repository;

import com.project.entity.Otp;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OtpRepository extends CrudRepository<Otp, String> {

    Optional<Otp> findByAccountNumber(String accountNumber);

    Optional<Otp> findByOtp(String otp);
}
