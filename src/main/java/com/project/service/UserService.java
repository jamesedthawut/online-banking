package com.project.service;

import com.project.entity.Otp;
import com.project.entity.User;
import com.project.exception.CustomException;
import com.project.exception.OtpException;
import com.project.exception.UserException;
import com.project.mapper.UserMapper;
import com.project.model.LoginRequest;
import com.project.model.OtpRequest;
import com.project.model.RegisterRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final RegisterService registerService;
    private final LoginService loginService;
    private final UserMapper userMapper;
    private final TokenService tokenService;
    private final OtpService otpService;
    private final EmailService emailService;

    public UserService(RegisterService registerService, LoginService loginService, UserMapper userMapper,
                       TokenService tokenService, OtpService otpService, EmailService emailService) {
        this.registerService = registerService;
        this.loginService = loginService;
        this.userMapper = userMapper;
        this.tokenService = tokenService;
        this.otpService = otpService;
        this.emailService = emailService;
    }

    public String register(RegisterRequest request) throws CustomException {
        registerService.create(request.getAccountNumber(), request.getName(), request.getSurname(), request.getEmail(),
                request.getPhoneNumber(), request.getPassword(), request.getConfirmPassword());

        String name = request.getName() + " " + request.getSurname();
        emailService.sendVerifyUser(request.getEmail(), name, request.getAccountNumber());

        return "Please check your email for verify your account.";
    }

    public String login(LoginRequest request) throws CustomException {

        Optional<User> opt = loginService.findByAccountNumber(request.getAccountNumber());

        if (opt.isEmpty()) {
            throw UserException.userNotFound();
        }

        User user = opt.get();

        if (!loginService.matchPassword(request.getPassword(), user.getPassword())) {
            throw UserException.passwordIncorrect();
        }

        if(!user.isVerification()) {
            emailService.sendVerifyUser(user.getEmail(), user.getName(), request.getAccountNumber());

            throw UserException.unVerify();
        }
        else {
            otpService.setOtp(request.getAccountNumber());

            emailService.sendOtp(user.getEmail(), user.getName(), user.getAccountNumber());

            return "OTP sent, Please check your email.";
        }
    }

    public String verify(String accountNumber) throws CustomException {
        Optional<User> opt = loginService.findByAccountNumber(accountNumber);

        if (opt.isEmpty()) {
            throw UserException.userNotFound();
        }

        registerService.setVerify(accountNumber);

        return "Register success, Your account has been verified";


    }

    public String otp(String accountNumber, OtpRequest request) throws CustomException {
        Optional<Otp> opt1 = otpService.findByOtp(request.getOtp());

        if (opt1.isEmpty()) {
            throw OtpException.OtpNotCorrect();
        }

        Otp otp = opt1.get();

        if (!otp.getAccountNumber().equals(accountNumber)) {
            throw OtpException.OtpNotCorrect();
        }

        Optional<User> opt2 = loginService.findByAccountNumber(otp.getAccountNumber());

        User user = opt2.get();

        return tokenService.tokenize(user);
    }

    public String refreshToken() throws CustomException {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String userId = (String) authentication.getPrincipal();

        if (userId.isEmpty()) {
            throw UserException.unauthorized();
        }

        Optional<User> opt = loginService.findById(userId);

        if (opt.isEmpty()) {
            throw UserException.userNotFound();
        }

        User user = opt.get();

        return tokenService.tokenize(user);
    }
}
