package com.project.exception;

public class EmailException extends CustomException{

    public EmailException(String message) {
        super(message);
    }

    public static EmailException templateNotFound() {
        return new EmailException("Template not found");
    }

}
