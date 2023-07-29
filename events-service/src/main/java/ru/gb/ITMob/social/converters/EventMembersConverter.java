package ru.gb.ITMob.social.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.ITMob.social.dtos.EventMembersDto;
import ru.gb.ITMob.social.entities.EventMembers;
import ru.gb.ITMob.social.services.EventMembersService;

@Component
@RequiredArgsConstructor
public class EventMembersConverter {
    public final EventMembersService eventTypesService;

    public EventMembersDto entityToDto(EventMembers eventMembers) {
        return new EventMembersDto(eventMembers.getId(), eventMembers.getEvent().getId(), eventMembers.getTitle(), eventMembers.getUsername(), eventMembers.getUpdatedAt(), eventMembers.getCreatedAt());
    }
}
