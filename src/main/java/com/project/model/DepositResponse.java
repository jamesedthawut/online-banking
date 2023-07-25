package com.project.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DepositResponse {

    private LocalDateTime date;

    private double deposit;

    private double balance;

}
