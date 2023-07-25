package com.project.api;

import com.project.exception.CustomException;
import com.project.model.LoginRequest;
import com.project.model.OtpRequest;
import com.project.model.RegisterRequest;
import com.project.service.OtpService;
import com.project.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserApi {

    private final UserService userService;

    private final OtpService otpService;

    public UserApi(UserService service, OtpService otpService) {
        this.userService = service;
        this.otpService = otpService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) throws CustomException {
        String response = userService.register(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) throws CustomException {
        String response = userService.login(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/verify-user/{accountNumber}")
    private ResponseEntity<String> verifyUser(@PathVariable String accountNumber) throws CustomException {
        String response = userService.verify(accountNumber);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/verify-otp/{accountNumber}")
    private ResponseEntity<String> verifyOtp(@PathVariable String accountNumber, @RequestBody OtpRequest request) throws CustomException {
        String response = userService.otp(accountNumber, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/refresh-token")
    public ResponseEntity<String> refreshToken() throws CustomException {
        String response = userService.refreshToken();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
