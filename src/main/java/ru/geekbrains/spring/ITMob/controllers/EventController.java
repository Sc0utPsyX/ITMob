package ru.geekbrains.spring.ITMob.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.ITMob.entities.Event;
import ru.geekbrains.spring.ITMob.services.EventService;
import ru.geekbrains.spring.ITMob.dtos.EventDto;
import ru.geekbrains.spring.ITMob.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public List<EventDto> findAllEvents() {
        return eventService.findAll().stream().map(p -> new EventDto(p.getId(), p.getEvent(), p.getDescription(), p.getEvent_date(), p.getEvent_place())).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EventDto findEventById(@PathVariable long id) {
        Event p = eventService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Событие не найдено, id: " + id));
        return new EventDto(p.getId(), p.getEvent(), p.getDescription(), p.getEvent_date(), p.getEvent_place());
    }

    @DeleteMapping("/{id}")
    public void deleteEventById(@PathVariable long id) {
        eventService.deleteById(id);
    }

}



