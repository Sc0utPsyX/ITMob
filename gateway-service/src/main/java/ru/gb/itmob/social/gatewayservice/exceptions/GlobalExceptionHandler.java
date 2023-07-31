package ru.gb.itmob.social.gatewayservice.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import ru.gb.itmob.social.gatewayservice.models.AppError;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<AppError> responseStatusExceptionHandler(ResponseStatusException exception) {
        return new ResponseEntity<>(
                new AppError(exception.getStatusCode().value(), exception.getMessage()),
                exception.getStatusCode()
        );
    }
}
