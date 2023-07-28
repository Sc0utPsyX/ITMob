package ru.geekbrains.spring.ITMob.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring.ITMob.entities.EventTypes;

import java.util.Optional;

@Repository
public interface EventTypesRepository extends JpaRepository<EventTypes, Long> {
    Optional<EventTypes> findByName(String name);
}