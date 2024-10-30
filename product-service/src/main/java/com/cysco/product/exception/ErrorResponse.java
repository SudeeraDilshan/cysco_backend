package com.cysco.product.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorResponse {
    private HttpStatus status;
    private String error;
    private String message;
    private String path;
}
