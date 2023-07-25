package com.project.service;

import com.project.exception.CustomException;
import com.project.exception.EmailException;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Service
public class EmailService {

    private final EmailSenderService emailSenderService;
    private final OtpService otpService;

    public EmailService(EmailSenderService emailSenderService, OtpService otpService) {
        this.emailSenderService = emailSenderService;
        this.otpService = otpService;
    }

    public void sendVerifyUser(String email, String name, String accountNumber) throws CustomException {
        String html = null;
        try {
            html = readEmailTemplate("email-verify-user.html");
        } catch (IOException e) {
            throw EmailException.templateNotFound();
        }

        String link = "http://localhost:8080/verify-user/" + accountNumber;
        html = html.replace("${NAME}", name);
        html = html.replace("${LINK}", link);

        String subject = "Welcome to Online Banking";
        emailSenderService.send(email, subject, html);
    }

    public void sendOtp(String email, String name, String accountNumber) throws CustomException {
        String html = null;

        try {
            html = readEmailTemplate("email-otp-login.html");
        } catch (IOException e) {
            throw EmailException.templateNotFound();
        }

        String otp = otpService.returnOtp(accountNumber);
        String refcode = otpService.returnRefcode(accountNumber);
        html = html.replace("${NAME}", name);
        html = html.replace("${OTP}", otp);
        html = html.replace("${REF}", refcode);

        String subject = "Welcome to Online Banking";
        emailSenderService.send(email, subject, html);
    }

    private String readEmailTemplate(String filename) throws IOException {
        File file = ResourceUtils.getFile("classpath:email/" + filename);
        return FileCopyUtils.copyToString(new FileReader(file));
    }
}
