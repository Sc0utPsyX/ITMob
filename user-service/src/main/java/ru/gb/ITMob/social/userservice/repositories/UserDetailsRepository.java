package ru.gb.ITMob.social.userservice.repositories;

import ru.gb.ITMob.social.userservice.entities.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    Optional<UserDetails> findByEmail(String email);
}
