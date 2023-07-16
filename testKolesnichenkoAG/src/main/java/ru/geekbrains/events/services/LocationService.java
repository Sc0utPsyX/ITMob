package ru.geekbrains.events.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.events.entities.Location;
import ru.geekbrains.events.repositories.LocationRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public Optional<Location> findByAddress(String address) {
        return locationRepository.findByAddress(address);
    }
}
