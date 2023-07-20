package ru.geekbrains.events.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TypeDto {

    private Long id;
    private String name;
    private String description;
    private List<TypeDto> events;
}
