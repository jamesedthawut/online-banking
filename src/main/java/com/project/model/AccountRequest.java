package com.project.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountRequest {

    private String accountNumber;

    private String phoneNumber;

    private double withdraw;

    private double deposit;

    private double transfer;

    private double balance;

    private LocalDateTime date;
}
