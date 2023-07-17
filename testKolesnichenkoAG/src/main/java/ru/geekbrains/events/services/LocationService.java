package ru.geekbrains.events.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.events.entities.EventLocations;
import ru.geekbrains.events.repositories.LocationRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public Optional<EventLocations> findByAddress(String address) {
        return locationRepository.findByAddress(address);
    }
}
