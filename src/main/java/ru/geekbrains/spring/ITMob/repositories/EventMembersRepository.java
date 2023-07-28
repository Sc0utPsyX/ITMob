package ru.geekbrains.spring.ITMob.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring.ITMob.entities.EventMembers;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventMembersRepository extends JpaRepository<EventMembers, Long> {
    List<EventMembers> findByTitleAndUsername(String title, String username);
    void deleteById(long id);
}
