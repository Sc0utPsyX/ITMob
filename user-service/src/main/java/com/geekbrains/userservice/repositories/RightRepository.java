package com.geekbrains.userservice.repositories;

import com.geekbrains.userservice.entities.Right;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RightRepository extends JpaRepository<Right, Long> {
    Optional<Right> findByName(String name);
}
