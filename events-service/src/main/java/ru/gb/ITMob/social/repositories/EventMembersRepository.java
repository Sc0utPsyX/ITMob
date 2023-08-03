package ru.gb.ITMob.social.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.ITMob.social.entities.EventMembers;

import java.util.List;

@Repository
public interface EventMembersRepository extends JpaRepository<EventMembers, Long> {
    List<EventMembers> findByTitleAndUsername(String title, String username);

    List<EventMembers> findByTitle(String title);
    void deleteById(long id);
}
