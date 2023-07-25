package com.project.exception;

public class UserException extends CustomException{

    public UserException(String message) {
        super(message);
    }

    public static UserException accountNumberNull() {
        return new UserException("Please insert account number");
    }

    public static UserException nameNull() {
        return new UserException("Please insert name ");
    }

    public static UserException emailNull() {
        return new UserException("Please insert email");
    }

    public static UserException phoneNumberNull() {
        return new UserException("Please insert phone number");
    }

    public static UserException passwordNull() {
        return new UserException("Please insert password");
    }

    public static UserException duplicatedAccountNumber() {
        return new UserException("Account number have been existed");
    }

    public static UserException userNotFound() {
        return new UserException("User not found");
    }

    public static UserException passwordIncorrect() {
        return new UserException("Password Incorrect, Please insert password again");
    }

    public static UserException passwordNotMatch() {
        return new UserException("Password and confirm not match , Please insert password again");
    }

    public static UserException unauthorized() {
        return new UserException("Unauthorized");
    }

    public static UserException unVerify() {
        return new UserException("Your account is unverified, Please verify your account.");
    }
}
