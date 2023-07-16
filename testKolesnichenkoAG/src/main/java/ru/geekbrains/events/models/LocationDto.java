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
public class LocationDto {

    private Long id;
    private String address;
   // private Point coordinates;
    private String detail;
    private List<EventDto> events;
}
