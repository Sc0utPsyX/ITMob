package ru.gb.ITMob.social.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventMembersDto {
    private Long id;
    private long event_id;
    private String tittle;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}