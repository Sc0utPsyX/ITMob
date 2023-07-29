package ru.gb.ITMob.social.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.ITMob.social.converters.EventConverter;
import ru.gb.ITMob.social.dtos.EventDto;
import ru.gb.ITMob.social.dtos.PageDto;
import org.springframework.data.jpa.domain.Specification;
import ru.gb.ITMob.social.entities.Event;
import ru.gb.ITMob.social.entities.EventMembers;
import ru.gb.ITMob.social.services.EventMembersService;
import ru.gb.ITMob.social.services.EventService;
import ru.gb.ITMob.social.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import ru.gb.ITMob.social.services.EventTypesService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final EventMembersService eventMembersService;
    private final EventConverter eventConverter;

    private final EventTypesService eventTypesService;

    @GetMapping
    public PageDto<EventDto> findEvents(
            @RequestParam(required = false, name = "title") String title,
            @RequestParam(required = false, name = "event_types_name") List<String> event_types_name,
            @RequestParam(required = false, name = "event_place") String event_place,
            @RequestParam(required = false, name = "username") String username, @RequestParam(defaultValue = "1", name = "p") Integer page) {

        if (page < 1) {
            page = 1;
        }

        Specification<Event> spec = eventService.createSpecByFilters(title, event_types_name, event_place);
        Page<EventDto> jpaPage = eventService.findAll(spec, page - 1).map(eventConverter::entityToDto);

        for (EventDto p : jpaPage) {
            String title_id = new StringBuilder().append(p.getId()).toString();
            List<EventMembers> eventMembers = eventMembersService.findByTitleAndUsername(title_id, username);
            if (!eventMembers.isEmpty()) {
                p.setFollow(true);
            }
        }

        PageDto<EventDto> out = new PageDto<>();
        out.setPage(jpaPage.getNumber());
        out.setItems(jpaPage.getContent());
        out.setTotalPages(jpaPage.getTotalPages());
        return out;
    }

    @GetMapping("/{id}")
    public EventDto findEventById(@PathVariable long id, @RequestParam(name = "username") String username) {
        Event p = eventService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Событие не найдено, id: " + id));
        Boolean isFollow = false;
        String title_id = new StringBuilder().append(p.getId()).toString();
        List<EventMembers> eventMembers = eventMembersService.findByTitleAndUsername(title_id, username);
        if (!eventMembers.isEmpty()){
            isFollow = true;
        }
        EventDto eventDto =  eventConverter.entityToDto(p);
        eventDto.setFollow(isFollow);
        return eventDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EventDto createNewEvent(@RequestBody EventDto eventDto) {
        Event p = eventService.createNewEvent(eventDto);
        return eventConverter.entityToDto(p);
    }

    @DeleteMapping("/{id}")
    public void deleteEventById(@PathVariable long id) {
        eventService.deleteById(id);
    }

}



