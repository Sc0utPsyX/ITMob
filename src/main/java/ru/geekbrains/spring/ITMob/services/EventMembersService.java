package ru.geekbrains.spring.ITMob.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.spring.ITMob.entities.Event;
import ru.geekbrains.spring.ITMob.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring.ITMob.entities.EventMembers;
import ru.geekbrains.spring.ITMob.repositories.EventMembersRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EventMembersService {
    private final EventService eventService;
    private final EventMembersRepository eventMembersRepository;

    @Transactional
    public EventMembers createEventMembers(long id, String username) {
        EventMembers eventMembers = new EventMembers();
        Event p = eventService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Событие не найдено, id: " + id));
        eventMembers.setEvent(p);
        String title_id = new StringBuilder().append(id).toString();
        eventMembers.setTitle(title_id);
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

    public void deleteById(long event_id){
        eventMembersRepository.deleteById(event_id);
    }

}
