package ru.geekbrains.spring.ITMob.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private Long id;
    private String event;
    private String description;
    private Timestamp event_date;
    private String event_place;

}
