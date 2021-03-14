package com.restdb.dbapi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
@Slf4j
public class DefaultRestExceptionHandler extends ResponseStatusExceptionHandler {

    @ExceptionHandler(StoredDatabaseConnectionException.class)
    public ResponseEntity<ErrorResponseDto> handleStoredDatabaseConnectionException(StoredDatabaseConnectionException exception) {
      log.error(exception.getMessage());
      ErrorResponseDto errorResponseDto = new ErrorResponseDto();
      errorResponseDto.setHttpCode(NOT_FOUND.value());
      errorResponseDto.setErrorMessage(exception.getMessage());

      return new ResponseEntity<>(errorResponseDto, NOT_FOUND);
    }

}
