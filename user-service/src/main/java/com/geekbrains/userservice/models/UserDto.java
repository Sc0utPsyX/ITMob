package com.geekbrains.userservice.models;

import com.geekbrains.userservice.entities.Right;
import com.geekbrains.userservice.entities.UserDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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
    private LocalDateTime registerDate;
    private boolean regConfirmed;
    private boolean active;
}
