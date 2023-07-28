package ru.gb.ITMob.social.userservice.exceptions;

import ru.gb.ITMob.social.userservice.models.AppError;
import org.springframework.http.ResponseEntity;
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
