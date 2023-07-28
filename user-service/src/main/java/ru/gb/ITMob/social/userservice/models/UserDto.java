package ru.gb.ITMob.social.userservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String login;
    private String username;
    private UserDetailsDto userDetails;
    private List<String> rights;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private Boolean regConfirmed;
    private Boolean active;
}
