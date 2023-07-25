package com.project;

import com.project.exception.CustomException;
import com.project.service.EmailService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestEmailService {

    @Autowired
    private EmailService emailService;

    @Test
    @Order(1)
    void testEmailVerify() throws CustomException {
        emailService.sendVerifyUser(testEmail.email, testEmail.name, testEmail.accountNumber);
    }

    @Test
    @Order(2)
    void testEmailOtp() throws CustomException {
        emailService.sendOtp(testEmail.email, testEmail.name, testEmail.accountNumber);
    }

    interface testEmail {

        String email = "jamesedthawut98@gmail.com";

        String name = "Sedthawut Sukprasit";

        String accountNumber = "1922343455";

    }
}
