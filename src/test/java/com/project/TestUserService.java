package com.project;

import com.project.entity.Otp;
import com.project.entity.User;
import com.project.exception.CustomException;
import com.project.service.LoginService;
import com.project.service.OtpService;
import com.project.service.RegisterService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestUserService {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private OtpService otpService;

    @Test
    @Order(1)
    void testRegister1() throws CustomException {
        String accountNumber = "1922343455";
        String name = "Sedthawut";
        String surname = "Sukprasit";
        String email = "jamesedthawut98@gmail.com";
        String phoneNumber = "0800608003";
        String password = "Sedthawut98";
        String confirmPassword = "Sedthawut98";

        User user = registerService.create(accountNumber, name, surname, email, phoneNumber, password, confirmPassword);

        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());
        Assertions.assertEquals("1922343455", user.getAccountNumber());
        Assertions.assertEquals("Sedthawut Sukprasit", user.getName());
        Assertions.assertEquals("jamesedthawut98@gmail.com", user.getEmail());
        Assertions.assertEquals("0800608003", user.getPhoneNumber());
        boolean isMatch = loginService.matchPassword("Sedthawut98", user.getPassword());
        Assertions.assertTrue(isMatch);
        Assertions.assertFalse(user.isVerification());
    }

    @Test
    @Order(2)
    void testRegister2() throws CustomException {
        String accountNumber = "3357079693";
        String name = "Bundit";
        String surname = "Saejiw";
        String email = "jamesedthawut98@gmail.com";
        String phoneNumber = "0869061029";
        String password = "alanvvake10";
        String confirmPassword = "alanvvake10";

        User user = registerService.create(accountNumber, name, surname, email, phoneNumber, password, confirmPassword);

        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());
        Assertions.assertEquals("3357079693", user.getAccountNumber());
        Assertions.assertEquals("Bundit Saejiw", user.getName());
        Assertions.assertEquals("jamesedthawut98@gmail.com", user.getEmail());
        Assertions.assertEquals("0869061029", user.getPhoneNumber());
        boolean isMatch = loginService.matchPassword("alanvvake10", user.getPassword());
        Assertions.assertTrue(isMatch);
        Assertions.assertFalse(user.isVerification());
    }

    @Test
    @Order(3)
    void testVerification1() throws CustomException {
        String accountNumber = "1922343455";

        Optional<User> opt = loginService.findByAccountNumber(accountNumber);

        Assertions.assertNotNull(opt);

        User user = opt.get();

        Assertions.assertNotNull(user.getId());
        Assertions.assertEquals(accountNumber, user.getAccountNumber());
        Assertions.assertFalse(user.isVerification());

        registerService.setVerify(accountNumber);
    }

    @Test
    @Order(4)
    void testVerification2() throws CustomException {
        String accountNumber = "3357079693";

        Optional<User> opt = loginService.findByAccountNumber(accountNumber);

        Assertions.assertNotNull(opt);

        User user = opt.get();

        Assertions.assertNotNull(user.getId());
        Assertions.assertEquals(accountNumber, user.getAccountNumber());
        Assertions.assertFalse(user.isVerification());

        registerService.setVerify(accountNumber);
    }

    @Test
    @Order(5)
    void testLogin() {

        String accountNumber = "1922343455";
        String password = "Sedthawut98";

        Optional<User> opt = loginService.findByAccountNumber(accountNumber);

        Assertions.assertNotNull(opt);

        User user = opt.get();

        Assertions.assertNotNull(user.getId());
        Assertions.assertEquals(accountNumber, user.getAccountNumber());
        boolean isMatch = loginService.matchPassword(password, user.getPassword());
        Assertions.assertTrue(isMatch);
        Assertions.assertTrue(user.isVerification());

        otpService.setOtp(accountNumber);
    }

    @Test
    @Order(6)
    void testOtp() throws CustomException {
        String accountNumber = "1922343455";

        Optional<Otp> opt = otpService.findByAccountNumber(accountNumber);

        Assertions.assertNotNull(opt);

        Otp otp = opt.get();

        Assertions.assertNotNull(otp.getId());
        Assertions.assertEquals(accountNumber, otp.getAccountNumber());
        Assertions.assertEquals(otpService.returnOtp(otp.getAccountNumber()), otp.getOtp());
    }
}

