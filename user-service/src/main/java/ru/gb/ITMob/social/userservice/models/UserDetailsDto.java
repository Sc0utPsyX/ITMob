package ru.gb.ITMob.social.userservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailsDto {

    private Long userId;
    private String about;
    private String address;
    private String city;
    private Gender sex;
    private LocalDate birthDate;
    private String email;

}
