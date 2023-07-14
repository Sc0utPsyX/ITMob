package ru.geekbrains.events.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.events.entities.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
