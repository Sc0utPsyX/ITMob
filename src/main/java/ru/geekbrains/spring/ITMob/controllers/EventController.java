package ru.geekbrains.spring.ITMob.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.ITMob.entities.Event;
import ru.geekbrains.spring.ITMob.services.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public List<Event> findAllEvents() {
        return eventService.findAll();
    }

    @GetMapping("/{id}")
    public Event findEventById(@PathVariable long id) {
        return eventService.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteEventById(@PathVariable long id) {
        eventService.deleteById(id);
    }

}
