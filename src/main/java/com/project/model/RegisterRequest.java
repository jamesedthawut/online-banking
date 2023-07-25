package com.project.model;

import lombok.Data;

@Data
public class RegisterRequest {

    private String accountNumber;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String password;
    private String confirmPassword;

}
