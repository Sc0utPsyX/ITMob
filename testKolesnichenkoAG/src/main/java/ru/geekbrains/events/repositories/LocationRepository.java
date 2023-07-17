package ru.geekbrains.events.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.events.entities.EventLocations;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<EventLocations, Long> {

    Optional<EventLocations> findByAddress(String address);
}
