package ru.geekbrains.spring.ITMob.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.ITMob.entities.EventTypes;
import ru.geekbrains.spring.ITMob.repositories.EventTypesRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventTypesService {
    private final EventTypesRepository eventTypesRepository;

    public List<EventTypes> findAll(){
        return eventTypesRepository.findAll();
    }

    public Optional<EventTypes> findById(long id){
        return eventTypesRepository.findById(id);
    }

    public Optional<EventTypes> findByName(String name) {
        return eventTypesRepository.findByName(name);
    }

}
