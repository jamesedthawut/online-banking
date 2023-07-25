package com.project.service;

import com.project.entity.Otp;
import com.project.exception.CustomException;
import com.project.exception.UserException;
import com.project.helper.OtpHelper;
import com.project.repository.OtpRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Service
public class OtpService {

    private final OtpRepository otpRepository;

    OtpHelper otpHelper;

    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    public OtpService(OtpRepository otpRepository) {
        this.otpRepository = otpRepository;
    }


    public Optional<Otp> findByOtp(String otp) {
        return otpRepository.findByOtp(otp);
    }

    public Optional<Otp> findByAccountNumber(String accountNumber) {
        return otpRepository.findByAccountNumber(accountNumber);
    }


    public void setOtp(String accountNumber) {
        Optional<Otp> opt = otpRepository.findByAccountNumber(accountNumber);

        if (opt.isPresent()) {
            otpRepository.delete(opt.get());
        }

        Otp otp = new Otp();

        otp.setAccountNumber(accountNumber);
        otp.setOtp(otpHelper.createRandomOtp());
        otp.setRefcode(otpHelper.createRefcode(chars));

        otpRepository.save(otp);
    }

    public String returnOtp(String accountNumber) throws CustomException {
        Optional<Otp> opt = otpRepository.findByAccountNumber(accountNumber);

        if(opt.isEmpty()) {
            throw UserException.userNotFound();
        }

        Otp otp = opt.get();

        return otp.getOtp();
    }

    public String returnRefcode(String accountNumber) throws CustomException {
        Optional<Otp> opt = otpRepository.findByAccountNumber(accountNumber);

        if(opt.isEmpty()) {
            throw UserException.userNotFound();
        }

        Otp otp = opt.get();

        return otp.getRefcode();
    }

}
