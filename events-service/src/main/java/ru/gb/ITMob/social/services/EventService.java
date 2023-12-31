package ru.gb.ITMob.social.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.ITMob.social.dtos.EventDto;
import ru.gb.ITMob.social.entities.Event;
import ru.gb.ITMob.social.entities.EventMembers;
import ru.gb.ITMob.social.entities.EventTypes;
import ru.gb.ITMob.social.exceptions.ResourceNotFoundException;
import ru.gb.ITMob.social.repositories.EventMembersRepository;
import ru.gb.ITMob.social.repositories.EventRepository;
import ru.gb.ITMob.social.repositories.specifications.EventSpecifications;
import ru.gb.ITMob.social.validators.EventValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final EventMembersService eventMembersService;

    private final EventValidator eventValidator;

    private final EventTypesService eventTypesService;

    public Page<Event> findAll(Specification<Event> spec, int page) {
        return eventRepository.findAll(spec, PageRequest.of(page, 4));
    }

    public Optional<Event> findById(long id){
        return eventRepository.findById(id);
    }

    @Transactional
    public void deleteById(Long id, String username){
        String title_id = new StringBuilder().append(id).toString();
        for (EventMembers p : eventMembersService.findByTitleAndUsername(title_id, username)) {
            eventMembersService.deleteById(p.getId());
        }
        eventRepository.deleteById(id);
    }

    @Transactional
    public Event createNewEvent(EventDto eventDto) {

        eventValidator.validate(eventDto);
        Event event = new Event();
        event.setId(eventDto.getId());
        event.setTitle(eventDto.getTitle());
        event.setAuthor(eventDto.getAuthor());
        event.setDescription(eventDto.getDescription());
        event.setEvent_date(eventDto.getEvent_date());
        event.setEvent_place(eventDto.getEvent_place());
        EventTypes e = eventTypesService.findByName(eventDto.getEvent_types_name()).orElseThrow(() -> new ResourceNotFoundException("Тип события не найден"));
        event.setEvent_types_id(e);
        eventRepository.save(event);

        eventMembersService.createEventMembers(event, eventDto.getAuthor());

        return event;
    }

    public Specification<Event> createSpecByFilters(String title, List<String> event_types_name, String event_place) {
        Specification<Event> spec = Specification.where(null);
        if (title != null) {
            spec = spec.and(EventSpecifications.titleLike("title", title));
        }
        if (event_place != null) {
            spec = spec.and(EventSpecifications.titleLike("event_place", event_place));
        }
        if (event_types_name!= null) {
            List<EventTypes> eventTypesList = new ArrayList<>();
            for (String e : event_types_name) {
                Optional<EventTypes> eventTypesPart = eventTypesService.findByName(e);
                if (!eventTypesPart.isEmpty()) {
                    eventTypesList.add(eventTypesPart.get());
                }
            }
            if (eventTypesList.size() > 0) {
                spec = spec.and(EventSpecifications.eventTypesLike(eventTypesList));
            }
        }
        return spec;
    }

}
