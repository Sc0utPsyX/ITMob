package ru.gb.ITMob.social.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.ITMob.social.entities.EventTypes;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private Long id;
    private EventTypes event_types_id;
    private String title;
    private String author;
    private String description;
    private Timestamp event_date;
    private String event_place;
    private boolean isfollow;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
