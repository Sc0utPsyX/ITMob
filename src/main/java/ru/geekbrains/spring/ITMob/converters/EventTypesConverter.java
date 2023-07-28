package ru.geekbrains.spring.ITMob.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring.ITMob.dtos.EventTypesDto;
import ru.geekbrains.spring.ITMob.entities.EventTypes;
import ru.geekbrains.spring.ITMob.services.EventTypesService;

@Component
@RequiredArgsConstructor
public class EventTypesConverter {
    public final EventTypesService eventTypesService;
    public EventTypesDto entityToDto(EventTypes eventTypes) {
        return new EventTypesDto(eventTypes.getId(), eventTypes.getName(), eventTypes.getPhoto_link(), eventTypes.getDescription(), eventTypes.getDescription_full(), eventTypes.getCreate_date(), eventTypes.getIs_default());
    }
}
