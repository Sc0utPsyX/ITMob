package ru.gb.ITMob.social.userservice.repositories;

import ru.gb.ITMob.social.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);
    Optional<User> findByUsername(String username);
}
