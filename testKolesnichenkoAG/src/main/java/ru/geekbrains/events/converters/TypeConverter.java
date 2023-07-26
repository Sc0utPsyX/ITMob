package ru.geekbrains.events.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.events.entities.Type;
import ru.geekbrains.events.models.TypeDto;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TypeConverter {

    private final EventConverter eventConverter;

    public TypeDto entityToDto(Type type) {
        TypeDto c = new TypeDto();
        c.setId(type.getId());
        c.setName(type.getName());
        c.setDescription(type.getDescription());
        c.setEvents(type.getEvents().stream().map(eventConverter::entityToDto).collect(Collectors.toList()));
        return c;
    }
}
