package ru.geekbrains.events.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.events.entities.EventLocation;
import ru.geekbrains.events.models.EventLocationDto;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EventLocationConverter {

    private final EventConverter eventConverter;

    public EventLocationDto entityToDto(EventLocation eventLocation) {
        EventLocationDto c = new EventLocationDto();
        c.setEventId(eventLocation.getEventId());
        c.setAddress(eventLocation.getAddress());
        c.setCoordinates(eventLocation.getCoordinates());
        c.setDetail(eventLocation.getDetail());
        c.setEvents(eventLocation.getEvents().stream().map(eventConverter::entityToDto).collect(Collectors.toList()));
        return c;
    }
}
