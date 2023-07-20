package ru.geekbrains.events.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.events.entities.Event;
import ru.geekbrains.events.entities.Type;
import ru.geekbrains.events.repositories.TypeRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TypeService {

    private final TypeRepository typeRepository;

    public Optional<Type> findByName(String name) {
        return typeRepository.findByName(name);
    }

    public List<Type> findAll() {
        return typeRepository.findAll();
    }
}
