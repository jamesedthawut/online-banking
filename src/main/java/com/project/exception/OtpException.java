package com.project.exception;

public class OtpException extends CustomException {

    public OtpException(String message) {
        super(message);
    }

    public static OtpException OtpNotCorrect() {
        return new OtpException("OTP is not correct");
    }
}
