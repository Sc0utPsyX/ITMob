package ru.geekbrains.events.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.events.entities.Event;
import ru.geekbrains.events.entities.EventLocations;
import ru.geekbrains.events.entities.Type;
import ru.geekbrains.events.models.EventDto;
import ru.geekbrains.events.services.LocationService;
import ru.geekbrains.events.services.TypeService;

@Component
@RequiredArgsConstructor
public class EventConverter {
    private final TypeService typeService;
    private final LocationService locationService;

    public EventDto entityToDto(Event p) {
        return new EventDto(p.getId(), p.getEvent(), p.getDescription(), p.getCreateData(),
                p.getOwnerId(), p.getEventDate(), p.getEventType().getName(), p.getEventLocations().getAddress());
    }

    public Event dtoToEntity(EventDto eventDto) {
        Event p = new Event();
        p.setId(eventDto.getId());
        p.setEvent(eventDto.getEvent());
        p.setDescription(eventDto.getDescription());
        p.setCreateData(eventDto.getCreateData());
        p.setOwnerId(eventDto.getOwnerId());
        p.setEventDate(eventDto.getEventDate());
        Type t = typeService.findByName(eventDto.getEventType()).orElseThrow(() -> new RuntimeException("Тип события не найден"));
        p.setEventType(t);
        EventLocations l = locationService.findByAddress(eventDto.getEventLocation()).orElseThrow(() -> new RuntimeException("Адресс локации не найден"));
        p.setEventLocations(l);
        return p;
    }
}
