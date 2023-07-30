package ru.gb.ITMob.social.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.gb.ITMob.social.converters.EventTypesConverter;
import ru.gb.ITMob.social.dtos.EventTypesDto;
import ru.gb.ITMob.social.services.EventTypesService;
import ru.gb.ITMob.social.entities.EventTypes;
import ru.gb.ITMob.social.exceptions.ResourceNotFoundException;

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

