package ru.gb.ITMob.social.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.ITMob.social.converters.EventMembersConverter;
import ru.gb.ITMob.social.dtos.EventMembersDto;
import ru.gb.ITMob.social.entities.Event;
import ru.gb.ITMob.social.entities.EventMembers;
import ru.gb.ITMob.social.exceptions.ResourceNotFoundException;
import ru.gb.ITMob.social.services.EventMembersService;
import ru.gb.ITMob.social.services.EventService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class EventMembersController {

    private final EventService eventService;
    private final EventMembersService eventMembersService;

    private final EventMembersConverter eventMembersConverter;

    @GetMapping("/api/v1/event_members")
    public List<EventMembersDto> findAll() {
        return eventMembersService.findAll().stream().map(eventMembersConverter::entityToDto).collect(Collectors.toList());
    }

    @GetMapping("/find_event_members")
    public List<EventMembersDto> findByTittleAndUsername(@RequestParam(name = "event_id") long event_id, @RequestParam(name = "username") String username) {
        String title_id = new StringBuilder().append(event_id).toString();
        return eventMembersService.findByTitleAndUsername(title_id, username).stream().map(eventMembersConverter::entityToDto).collect(Collectors.toList());
    }

    @GetMapping("/followers_event_members")
    public List<EventMembersDto> findByTittle(@RequestParam(name = "event_id") long event_id) {
        String title_id = new StringBuilder().append(event_id).toString();
        return eventMembersService.findByTitle(title_id).stream().map(eventMembersConverter::entityToDto).collect(Collectors.toList());
    }

    @PostMapping("/add_event_members")
    @ResponseStatus(HttpStatus.CREATED)
    public EventMembersDto addEventMembers(@RequestParam(name = "event_id") long event_id, @RequestParam(name = "username") String username) {
        Event event = eventService.findById(event_id).orElseThrow(() -> new ResourceNotFoundException("Событие не найдено, id: " + event_id));
        EventMembers p = eventMembersService.createEventMembers(event, username);
        return eventMembersConverter.entityToDto(p);
    }

    @DeleteMapping("/delete_event_members")
    public void deleteByIdAndUsername(@RequestParam(name = "event_id") long event_id, @RequestParam(name = "username") String username) {
        String title_id = new StringBuilder().append(event_id).toString();
        for (EventMembers p : eventMembersService.findByTitleAndUsername(title_id, username)) {
            eventMembersService.deleteById(p.getId());
        }
    }
}
