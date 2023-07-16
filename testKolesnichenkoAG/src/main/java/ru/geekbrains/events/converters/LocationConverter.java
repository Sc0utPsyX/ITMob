package ru.geekbrains.events.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.events.entities.Location;
import ru.geekbrains.events.models.LocationDto;

@Component
@RequiredArgsConstructor
public class LocationConverter {

    private final EventConverter eventConverter;

    public LocationDto entityToDto(Location eventLocation) {
        LocationDto c = new LocationDto();
        c.setId(eventLocation.getId());
        c.setAddress(eventLocation.getAddress());
        // c.setCoordinates(eventLocation.getCoordinates());
        c.setDetail(eventLocation.getDetail());
        //c.setEvents(eventLocation.getEvents().stream().map(eventConverter::entityToDto).collect(Collectors.toList()));
        return c;
    }
}
