package com.project.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HistoryResponse {

    private LocalDateTime date;

    private double balance;

    private double deposit;

    private double withdraw;

    private String note;
}
