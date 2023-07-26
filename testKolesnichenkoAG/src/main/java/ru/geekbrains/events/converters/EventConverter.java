package ru.geekbrains.events.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.events.entities.Event;
import ru.geekbrains.events.entities.Locations;
import ru.geekbrains.events.entities.Type;
import ru.geekbrains.events.models.EventDto;
import ru.geekbrains.events.services.LocationService;
import ru.geekbrains.events.services.TypeService;

@Component
@RequiredArgsConstructor
public class EventConverter {
    private final TypeService typeService;
    private final LocationService locationService;

    public EventDto entityToDto(Event event) {
        return new EventDto(event.getId(), event.getTitle(), event.getDescription(), event.getOwnerId(),
                event.getEventDate(), event.getType().getName(), event.getLocation().getAddress());
    }

    public Event dtoToEntity(EventDto eventDto) {
        Event p = new Event();
        p.setId(eventDto.getId());
        p.setTitle(eventDto.getTitle());
        p.setDescription(eventDto.getDescription());
        p.setOwnerId(eventDto.getOwnerId());
        p.setEventDate(eventDto.getEventDate());
        Type t = typeService.findByName(eventDto.getTypeName()).orElseThrow(() -> new RuntimeException("Тип события не найден"));
        p.setType(t);
        Locations l = locationService.findByAddress(eventDto.getLocationAddress()).orElseThrow(() -> new RuntimeException("Адресс локации не найден"));
        p.setLocation(l);
        return p;
    }
}
