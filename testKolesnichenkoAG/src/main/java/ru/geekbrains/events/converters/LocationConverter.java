package ru.geekbrains.events.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.events.entities.EventLocations;
import ru.geekbrains.events.models.LocationDto;

@Component
@RequiredArgsConstructor
public class LocationConverter {

    private final EventConverter eventConverter;

    public LocationDto entityToDto(EventLocations eventLocations) {
        LocationDto c = new LocationDto();
        c.setId(eventLocations.getId());
        c.setAddress(eventLocations.getAddress());
        // c.setCoordinates(eventLocation.getCoordinates());
        c.setDetail(eventLocations.getDetail());
        //c.setEvents(eventLocation.getEvents().stream().map(eventConverter::entityToDto).collect(Collectors.toList()));
        return c;
    }
}
