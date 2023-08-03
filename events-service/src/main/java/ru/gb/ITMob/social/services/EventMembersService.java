package ru.gb.ITMob.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.ITMob.social.entities.Event;
import ru.gb.ITMob.social.exceptions.ResourceNotFoundException;
import ru.gb.ITMob.social.entities.EventMembers;
import ru.gb.ITMob.social.repositories.EventMembersRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EventMembersService {
    private final EventMembersRepository eventMembersRepository;

    public EventMembers createEventMembers(Event event , String username) {
        EventMembers eventMembers = new EventMembers();
        eventMembers.setEvent(event);
        eventMembers.setTitle(event.getId().toString());
        eventMembers.setUsername(username);
        eventMembersRepository.save(eventMembers);
        return eventMembers;
    }

    public List<EventMembers> findAll(){
        return eventMembersRepository.findAll();
    }

    public List<EventMembers> findByTitleAndUsername(String title, String username) {
        return eventMembersRepository.findByTitleAndUsername(title, username);
    }

    public List<EventMembers> findByTitle(String title) {
        return eventMembersRepository.findByTitle(title);
    }

    public void deleteById(long event_id){
        eventMembersRepository.deleteById(event_id);
    }

}
