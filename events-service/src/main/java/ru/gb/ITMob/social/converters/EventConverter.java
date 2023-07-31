package ru.gb.ITMob.social.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.ITMob.social.dtos.EventDto;
import ru.gb.ITMob.social.entities.Event;
import ru.gb.ITMob.social.entities.EventTypes;
import ru.gb.ITMob.social.services.EventTypesService;
import ru.gb.ITMob.social.exceptions.ResourceNotFoundException;

@Component
@RequiredArgsConstructor
public class EventConverter {
    private final EventTypesService eventTypesService;

    public EventDto entityToDto(Event event) {
        return new EventDto(event.getId(), event.getEvent_types_id(), event.getEvent_types_id().getName(), event.getTitle(), event.getAuthor(), event.getDescription(), event.getEvent_date(), event.getEvent_place(), false, event.getUpdatedAt(), event.getCreatedAt());
    }

    public Event dtoToEntity(EventDto eventDto) {

        Event p = new Event();
        p.setId(eventDto.getId());
        p.setTitle(eventDto.getTitle());
        p.setAuthor(eventDto.getAuthor());
        p.setDescription(eventDto.getDescription());
        p.setEvent_date(eventDto.getEvent_date());
        p.setEvent_place(eventDto.getEvent_place());
        EventTypes e = eventTypesService.findByName(eventDto.getEvent_types_name()).orElseThrow(() -> new ResourceNotFoundException("Тип события не найден"));
        p.setEvent_types_id(e);
        return p;
    }
}
