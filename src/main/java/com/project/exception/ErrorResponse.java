package com.project.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {

    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime localDateTime;
}
