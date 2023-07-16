package ru.geekbrains.events.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.events.entities.Event;
import ru.geekbrains.events.entities.Location;
import ru.geekbrains.events.entities.Type;
import ru.geekbrains.events.models.EventDto;
import ru.geekbrains.events.models.ResourceNotFoundException;
import ru.geekbrains.events.repositories.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final TypeService typeService;
    private final LocationService locationService;

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    public Event createNewEvent(EventDto eventDto) {
        Event event = new Event();
        event.setEvent(eventDto.getEvent());
        event.setDescription(eventDto.getDescription());
        event.setOwnerId(eventDto.getOwnerId());
        Type type = typeService.findByName(eventDto.getEventType()).
                orElseThrow(() -> new ResourceNotFoundException("Type not found"));
        event.setEventType(type);
        Location location = locationService.findByAddress(eventDto.getEventLocation()).
                orElseThrow(() -> new ResourceNotFoundException("Address location not found"));
        event.setEventLocation(location);
        eventRepository.save(event);
        return event;
    }
}
