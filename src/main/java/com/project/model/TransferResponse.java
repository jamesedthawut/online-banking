package com.project.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransferResponse {

    private LocalDateTime date;

    private double withdraw;

    private double balance;

    private String note;
}
