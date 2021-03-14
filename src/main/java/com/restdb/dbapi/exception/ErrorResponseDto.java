package com.restdb.dbapi.exception;

import lombok.Data;

@Data
public class ErrorResponseDto {
    private int httpCode;
    private String errorMessage;
}
