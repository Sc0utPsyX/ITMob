package ru.geekbrains.events.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.events.entities.Locations;
import ru.geekbrains.events.models.LocationDto;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LocationConverter {

    private final EventConverter eventConverter;

    public LocationDto entityToDto(Locations locations) {
        LocationDto c = new LocationDto();
        c.setId(locations.getId());
        c.setAddress(locations.getAddress());
        // c.setCoordinates(eventLocation.getCoordinates());
        c.setDetail(locations.getDetail());
        c.setEvents(locations.getEvents().stream().map(eventConverter::entityToDto).collect(Collectors.toList()));
        return c;
    }
}
