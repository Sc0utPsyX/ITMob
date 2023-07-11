package com.geekbrains.userservice.exceptions;

import com.geekbrains.userservice.models.AppError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler
    public ResponseEntity<AppError> handleResponseStatusException(ResponseStatusException exception) {
        return new ResponseEntity<>(
                new AppError(exception.getStatusCode().value(), exception.getMessage()),
                exception.getStatusCode()
        );
    }

}
