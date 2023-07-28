package ru.geekbrains.spring.ITMob.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationException extends RuntimeException {
    private List<ValidationFieldError> fields;

    public ValidationException(String message, List<ValidationFieldError> fields) {
        super(message);
        this.fields = fields;
    }
}
