package com.cysco.category.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SuccessResponse <T>{
    private String msg;
    private T data;
    private HttpStatus status;
}
