package ru.geekbrains.spring.ITMob.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.ITMob.entities.Event;
import ru.geekbrains.spring.ITMob.repositories.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public List<Event> findAll(){
        return eventRepository.findAll();
    }

    public Optional<Event> findById(long id){
        return eventRepository.findById(id);
    }

    public void deleteById(Long id){
        eventRepository.deleteById(id);
    }
}
