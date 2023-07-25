package com.project.model;

import lombok.Data;

@Data
public class LoginRequest {

    private String accountNumber;
    private String password;

}
