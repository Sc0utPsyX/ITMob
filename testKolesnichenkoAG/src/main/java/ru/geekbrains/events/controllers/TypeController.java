package ru.geekbrains.events.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.events.converters.TypeConverter;
import ru.geekbrains.events.models.TypeDto;
import ru.geekbrains.events.services.TypeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/types")
@RequiredArgsConstructor
public class TypeController {

    private final TypeService typeService;
    private final TypeConverter typeConverter;

    @GetMapping
    public List<TypeDto> findAllTypes() {
        return typeService.findAll().stream().map(typeConverter::entityToDto).collect(Collectors.toList());
    }
}
