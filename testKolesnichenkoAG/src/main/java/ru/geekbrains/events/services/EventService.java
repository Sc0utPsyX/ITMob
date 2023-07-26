package ru.geekbrains.events.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.events.entities.Event;
import ru.geekbrains.events.entities.Locations;
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
        event.setTitle(eventDto.getTitle());
        event.setDescription(eventDto.getDescription());
        event.setOwnerId(eventDto.getOwnerId());
        Type type = typeService.findByName(eventDto.getTypeName()).
                orElseThrow(() -> new ResourceNotFoundException("Type not found"));
        event.setType(type);
        Locations locations = locationService.findByAddress(eventDto.getLocationAddress()).
                orElseThrow(() -> new ResourceNotFoundException("Address location not found"));
        event.setLocation(locations);
        eventRepository.save(event);
        return event;
    }

    /*public void createEvent(Event event) {
        event.setTypes(List.of(typeService.getEventType()));
        eventRepository.save(event);
    }*/
}
