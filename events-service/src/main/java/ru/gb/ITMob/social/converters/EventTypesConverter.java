package ru.gb.ITMob.social.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.ITMob.social.dtos.EventTypesDto;
import ru.gb.ITMob.social.entities.EventTypes;
import ru.gb.ITMob.social.services.EventTypesService;

@Component
@RequiredArgsConstructor
public class EventTypesConverter {
    public final EventTypesService eventTypesService;
    public EventTypesDto entityToDto(EventTypes eventTypes) {
        return new EventTypesDto(eventTypes.getId(), eventTypes.getName(), eventTypes.getPhoto_link(), eventTypes.getDescription(), eventTypes.getDescription_full(), eventTypes.getCreate_date(), eventTypes.getIs_default());
    }
}
