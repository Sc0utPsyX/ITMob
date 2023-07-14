package ru.geekbrains.events.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.events.entities.EventLocation;
import ru.geekbrains.events.repositories.EventLocationRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventLocationService {

    private final EventLocationRepository eventLocationRepository;

    public Optional<EventLocation> findByTitle(String title) {
        return eventLocationRepository.findByTitle(title);
    }
}
