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
    private String title;
    private String description;
    private Long ownerId;
    private Date eventDate;
    private String typeName;
    private String LocationAddress;

    public String getTypeName() {
        return typeName;
    }

    public String getLocationAddress() {
        return LocationAddress;
    }
}
