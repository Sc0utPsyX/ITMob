package ru.gb.ITMob.social.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.gb.ITMob.social.entities.Event;
import ru.gb.ITMob.social.entities.EventTypes;

import java.util.List;

public class EventSpecifications {

    public static Specification<Event> titleLike(String title, String titlePart) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get(title), String.format("%%%s%%", titlePart));
    }

    public static Specification<Event> eventTypesLike(List<EventTypes> eventTypesPart) {
        return (root, criteriaQuery, criteriaBuilder) -> root.get("event_types_id").in(eventTypesPart);
    }

}
