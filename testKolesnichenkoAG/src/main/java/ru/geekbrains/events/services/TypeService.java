package ru.geekbrains.events.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.events.entities.Type;
import ru.geekbrains.events.repositories.TypeRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TypeService {

    private final TypeRepository typeRepository;

    public Optional<Type> findByTitle(String title) {
        return typeRepository.findByTitle(title);
    }
}
