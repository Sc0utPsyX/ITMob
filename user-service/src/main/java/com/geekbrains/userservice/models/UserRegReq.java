package com.geekbrains.userservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegReq {
    private String login;
    private String password;
    private String email;
    private List<String> rights;
}
