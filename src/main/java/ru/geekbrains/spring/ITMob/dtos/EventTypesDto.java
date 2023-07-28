package ru.geekbrains.spring.ITMob.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventTypesDto {
    private Long id;
    private String name;
    private String photo_link;
    private String description;
    private String description_full;
    private Timestamp create_date;
    private Boolean is_default;
}


