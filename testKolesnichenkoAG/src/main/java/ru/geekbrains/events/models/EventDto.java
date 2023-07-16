package ru.geekbrains.events.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventDto {

    private Long id;
    private String event;
    private String description;
    private LocalDateTime createData;
    private Long ownerId;
    private Date eventDate;
    private String eventType;
    private String eventLocation;

    public String getEventType() {
        return eventType;
    }

    public String getEventLocation() {
        return eventLocation;
    }
}
