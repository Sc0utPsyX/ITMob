package ru.geekbrains.spring.ITMob.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring.ITMob.dtos.EventDto;
import ru.geekbrains.spring.ITMob.entities.Event;
import ru.geekbrains.spring.ITMob.entities.EventTypes;
import ru.geekbrains.spring.ITMob.services.EventTypesService;
import ru.geekbrains.spring.ITMob.exceptions.ResourceNotFoundException;

@Component
@RequiredArgsConstructor
public class EventConverter {
    private final EventTypesService eventTypesService;

    public EventDto entityToDto(Event event) {
        return new EventDto(event.getId(), event.getEvent_types_id(), event.getTitle(), event.getAuthor(), event.getDescription(), event.getEvent_date(), event.getEvent_place(), false, event.getUpdatedAt(), event.getCreatedAt());
    }

    public Event dtoToEntity(EventDto eventDto) {

        Event p = new Event();
        p.setId(eventDto.getId());
        p.setTitle(eventDto.getTitle());
        p.setAuthor(eventDto.getAuthor());
        p.setDescription(eventDto.getDescription());
        p.setEvent_date(eventDto.getEvent_date());
        p.setEvent_place(eventDto.getEvent_place());
        EventTypes e = eventTypesService.findById(eventDto.getEvent_types_id().getId()).orElseThrow(() -> new ResourceNotFoundException("Тип события не найден"));
        p.setEvent_types_id(e);
        return p;
    }
}
