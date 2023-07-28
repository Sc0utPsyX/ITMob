package ru.geekbrains.spring.ITMob.validators;

import org.springframework.stereotype.Component;
import ru.geekbrains.spring.ITMob.dtos.EventDto;
import ru.geekbrains.spring.ITMob.exceptions.ValidationException;
import ru.geekbrains.spring.ITMob.exceptions.ValidationFieldError;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventValidator implements Validator<EventDto> {
    @Override
    public void validate(EventDto p) {
        List<ValidationFieldError> errors = new ArrayList<>();
        if (p.getTitle().length() < 3 || p.getTitle().length() > 255) {
            errors.add(new ValidationFieldError("title", p.getTitle(), "Длина названия события должна находиться в пределах 3-255 символов"));
        }
        if (!errors.isEmpty()) {
            throw new ValidationException("Событие не прошло проверку", errors);
        }
    }
}
