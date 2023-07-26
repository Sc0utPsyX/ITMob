package ru.geekbrains.events.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.events.entities.Locations;

import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Locations, Long> {

    Optional<Locations> findByAddress(String address);
}
