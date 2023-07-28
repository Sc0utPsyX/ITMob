package ru.geekbrains.spring.ITMob.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.ITMob.converters.EventTypesConverter;
import ru.geekbrains.spring.ITMob.dtos.EventTypesDto;
import ru.geekbrains.spring.ITMob.services.EventTypesService;
import ru.geekbrains.spring.ITMob.entities.EventTypes;
import ru.geekbrains.spring.ITMob.exceptions.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/event_types")
@RequiredArgsConstructor
public class EventTypesController {

    private final EventTypesService eventTypesService;

    private final EventTypesConverter eventTypesConverter;

    @GetMapping
    public List<EventTypesDto> findAllEventTypes() {
        return eventTypesService.findAll().stream().map(eventTypesConverter::entityToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EventTypesDto findEventTypesById(@PathVariable long id) {
        EventTypes p = eventTypesService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Тип события не найдено, id: " + id));
        return eventTypesConverter.entityToDto(p);
    }

}

