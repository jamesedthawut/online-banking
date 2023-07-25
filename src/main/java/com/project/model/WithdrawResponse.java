package com.project.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WithdrawResponse {

    private LocalDateTime date;

    private double withdraw;

    private double balance;
}
