package ru.geekbrains.spring.ITMob.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring.ITMob.dtos.EventMembersDto;
import ru.geekbrains.spring.ITMob.entities.EventMembers;
import ru.geekbrains.spring.ITMob.services.EventMembersService;

@Component
@RequiredArgsConstructor
public class EventMembersConverter {
    public final EventMembersService eventTypesService;

    public EventMembersDto entityToDto(EventMembers eventMembers) {
        return new EventMembersDto(eventMembers.getId(), eventMembers.getEvent().getId(), eventMembers.getTitle(), eventMembers.getUsername(), eventMembers.getUpdatedAt(), eventMembers.getCreatedAt());
    }
}
