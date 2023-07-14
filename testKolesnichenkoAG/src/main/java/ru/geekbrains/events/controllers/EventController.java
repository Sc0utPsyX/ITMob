package ru.geekbrains.events.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.events.converters.EventConverter;
import ru.geekbrains.events.entities.Event;
import ru.geekbrains.events.models.EventDto;
import ru.geekbrains.events.models.ResourceNotFoundException;
import ru.geekbrains.events.services.EventService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final EventConverter eventConverter;

    @GetMapping
    public List<EventDto> findAllEvents() {
        return eventService.findAll().stream().map(eventConverter::entityToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EventDto findEventById(@PathVariable Long id) {
        Event p = eventService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Событие с id: " + id + " не найден"));
        return eventConverter.entityToDto(p);
    }

    @PostMapping
    public EventDto crateNewEvent(@RequestBody EventDto eventDto) {
        Event p = eventService.createNewEvent(eventDto);
        return eventConverter.entityToDto(p);
    }

    @DeleteMapping("/{id}")
    public void deleteEventById(@PathVariable Long id) {
        eventService.deleteById(id);
    }
}
