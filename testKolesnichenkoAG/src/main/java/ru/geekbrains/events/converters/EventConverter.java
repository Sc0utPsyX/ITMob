package ru.geekbrains.events.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.events.entities.Event;
import ru.geekbrains.events.entities.Type;
import ru.geekbrains.events.models.EventDto;
import ru.geekbrains.events.services.TypeService;

@Component
@RequiredArgsConstructor
public class EventConverter {
    private final TypeService typeService;

    public EventDto entityToDto(Event p) {
        return new EventDto(p.getId(), p.getEvent(), p.getDescription(), p.getCreateData(),
                p.getOwnerId(), p.getEventDate(), p.getEventType().getName());
    }

    public Event dtoToEntity(EventDto eventDto) {
        Event p = new Event();
        p.setId(eventDto.getId());
        p.setEvent(eventDto.getEvent());
        p.setDescription(eventDto.getDescription());
        p.setCreateData(eventDto.getCreateData());
        p.setOwnerId(eventDto.getOwnerId());
        p.setEventDate(eventDto.getEventDate());
        Type t = typeService.findByTitle(eventDto.getEventType()).orElseThrow(() -> new RuntimeException("Тип события не найден"));
        p.setEventType(t);
        return p;
    }
}
